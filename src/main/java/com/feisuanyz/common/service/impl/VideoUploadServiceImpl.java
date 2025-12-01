package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.*;
import com.feisuanyz.common.dto.CustomCoverRequest;
import com.feisuanyz.common.dto.FrameCoverRequest;
import com.feisuanyz.common.dto.InitUploadRequest;
import com.feisuanyz.common.dto.InitUploadResponse;
import com.feisuanyz.common.dto.MergeUploadRequest;
import com.feisuanyz.common.dto.MergeUploadResponse;
import com.feisuanyz.common.dto.QueryUploadStatusRequest;
import com.feisuanyz.common.dto.QueryUploadStatusResponse;
import com.feisuanyz.common.dto.UploadChunkRequest;
import com.feisuanyz.common.entity.VideoInfo;
import com.feisuanyz.common.entity.VideoTranscodeTask;
import com.feisuanyz.common.entity.VideoUploadChunk;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.repository.VideoTranscodeTaskRepository;
import com.feisuanyz.common.repository.VideoUploadChunkRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoUploadService;
import com.feisuanyz.common.util.FileUtil;
import com.feisuanyz.common.util.VideoUtil;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 视频上传服务实现类
 *
 * @author tianchunxu
 */
@Slf4j
@Service
public class VideoUploadServiceImpl implements VideoUploadService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.chunk.size}")
    private Long defaultChunkSize;

    private final VideoUploadChunkRepository videoUploadChunkRepository;
    private final VideoInfoRepository videoInfoRepository;
    private final VideoTranscodeTaskRepository videoTranscodeTaskRepository;

    public VideoUploadServiceImpl(VideoUploadChunkRepository videoUploadChunkRepository,
                                   VideoInfoRepository videoInfoRepository,
                                   VideoTranscodeTaskRepository videoTranscodeTaskRepository) {
        this.videoUploadChunkRepository = videoUploadChunkRepository;
        this.videoInfoRepository = videoInfoRepository;
        this.videoTranscodeTaskRepository = videoTranscodeTaskRepository;
    }

    @Override
    public RestResult<InitUploadResponse> initUpload(InitUploadRequest request) {
        // 校验文件格式
        if (!FileUtil.isSupportedFormat(request.getFileName())) {
            return RestResult.fail("000001", "不支持的文件格式");
        }

        // 校验文件大小是否超过10GB限制
        if (request.getFileSize() > 10 * 1024 * 1024 * 1024L) {
            return RestResult.fail("000001", "文件大小超出限制");
        }

        // 生成唯一的上传任务ID
        String uploadId = UUID.randomUUID().toString().replace("-", "");

        // 创建初始分片上传记录
        // 这里只是初始化了上传任务ID，具体分片上传时再插入分片记录

        InitUploadResponse response = new InitUploadResponse();
        response.setUploadId(uploadId);
        return RestResult.success(response);
    }

    @Override
    public RestResult<Void> uploadChunk(UploadChunkRequest request) {
        // 验证上传任务ID是否存在且有效
        Optional<VideoUploadChunk> existingChunk = videoUploadChunkRepository.findByUploadIdAndChunkIndex(
                request.getUploadId(), request.getChunkIndex());
        if (existingChunk.isPresent()) {
            return RestResult.fail("000001", "该分片已存在");
        }

        // 存储当前分片到指定路径并记录相关信息
        String chunkPath = Paths.get(uploadPath, request.getUploadId(), "chunks",
                "chunk_" + request.getChunkIndex()).toString();
        if (!FileUtil.mkdir(new File(chunkPath).getParent())) {
            return RestResult.fail("000001", "创建分片目录失败");
        }

        // 假设chunkData是Base64编码的字符串
        byte[] chunkBytes = Base64.getDecoder().decode(request.getChunkData());

        if (!FileUtil.writeFile(chunkPath, chunkBytes)) {
            return RestResult.fail("000001", "分片写入失败");
        }

        // 验证MD5校验值
        String calculatedMd5 = FileUtil.getFileMd5(chunkBytes);
        if (!calculatedMd5.equals(request.getMd5Checksum())) {
            return RestResult.fail("000001", "分片MD5校验失败");
        }

        // 创建分片记录
        VideoUploadChunk chunk = new VideoUploadChunk();
        chunk.setUploadId(request.getUploadId());
        chunk.setChunkIndex(request.getChunkIndex());
        chunk.setChunkSize((long) chunkBytes.length);
        chunk.setChunkPath(chunkPath);
        chunk.setMd5Checksum(request.getMd5Checksum());
        chunk.setCreateTime(LocalDateTime.now());
        chunk.setUpdateTime(LocalDateTime.now());
        videoUploadChunkRepository.save(chunk);

        return RestResult.success();
    }

    @Override
    public RestResult<MergeUploadResponse> mergeUpload(MergeUploadRequest request) {
        // 验证所有分片是否都已成功上传
        List<VideoUploadChunk> chunks = videoUploadChunkRepository.findByUploadIdOrderByChunkIndexAsc(request.getUploadId());
        if (chunks.isEmpty()) {
            return RestResult.fail("000001", "部分分片尚未上传完成");
        }

        // 检查是否所有分片都已上传
        int totalChunks = chunks.stream()
                .mapToInt(VideoUploadChunk::getChunkIndex)
                .max()
                .orElse(-1) + 1;

        Set<Integer> uploadedIndexes = new HashSet<>();
        for (VideoUploadChunk chunk : chunks) {
            uploadedIndexes.add(chunk.getChunkIndex());
        }

        if (uploadedIndexes.size() != totalChunks) {
            return RestResult.fail("000001", "部分分片尚未上传完成");
        }

        // 合并所有分片为完整视频文件
        String mergedFilePath = Paths.get(uploadPath, request.getUploadId(), "merged_video.mp4").toString();
        List<String> chunkPaths = new ArrayList<>();
        for (int i = 0; i < totalChunks; i++) {
            chunkPaths.add(Paths.get(uploadPath, request.getUploadId(), "chunks", "chunk_" + i).toString());
        }

        if (!VideoUtil.mergeChunks(mergedFilePath, chunkPaths)) {
            return RestResult.fail("000001", "合并分片失败");
        }

        // 记录完整视频文件的基本信息至video_info表中
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setTitle("默认标题");
        videoInfo.setDescription("默认描述");
        videoInfo.setUploaderId(1L); // 默认上传者ID
        videoInfo.setCategoryId(1L); // 默认分区ID
        videoInfo.setFileSize(FileUtil.getFileSize(mergedFilePath));
        videoInfo.setOriginalFilePath(mergedFilePath);
        videoInfo.setTranscodedStatus(0); // 未开始转码
        videoInfo.setVisibility(1); // 默认公开
        videoInfo.setCreateTime(LocalDateTime.now());
        videoInfo.setUpdateTime(LocalDateTime.now());
        VideoInfo savedVideoInfo = videoInfoRepository.save(videoInfo);

        // 删除临时分片文件及相关记录
        for (String chunkPath : chunkPaths) {
            FileUtil.deleteFile(chunkPath);
        }
        videoUploadChunkRepository.deleteByUploadId(request.getUploadId());

        // 触发自动转码流程，创建360P/720P/1080P三种清晰度的转码任务
        createTranscodeTasks(savedVideoInfo.getId());

        MergeUploadResponse response = new MergeUploadResponse();
        response.setVideoId(savedVideoInfo.getId());
        return RestResult.success(response);
    }

    @Override
    public RestResult<QueryUploadStatusResponse> queryUploadStatus(QueryUploadStatusRequest request) {
        List<VideoUploadChunk> chunks = videoUploadChunkRepository.findByUploadIdOrderByChunkIndexAsc(request.getUploadId());
        QueryUploadStatusResponse response = new QueryUploadStatusResponse();
        List<Integer> uploadedChunks = new ArrayList<>();
        for (VideoUploadChunk chunk : chunks) {
            uploadedChunks.add(chunk.getChunkIndex());
        }
        response.setUploadedChunks(uploadedChunks);
        response.setTotalChunks(chunks.size());
        return RestResult.success(response);
    }

    @Override
    public RestResult<Void> customCover(CustomCoverRequest request) {
        // 验证视频是否存在
        Optional<VideoInfo> optionalVideo = videoInfoRepository.findById(request.getVideoId());
        if (!optionalVideo.isPresent()) {
            return RestResult.fail("000001", "视频不存在");
        }

        // 将封面图片保存至服务器并更新视频信息中的封面URL
        String coverPath = Paths.get(uploadPath, "covers", request.getVideoId() + ".jpg").toString();
        if (!FileUtil.mkdir(new File(coverPath).getParent())) {
            return RestResult.fail("000001", "创建封面目录失败");
        }

        // 假设coverImage是Base64编码的字符串
        byte[] coverBytes = Base64.getDecoder().decode(request.getCoverImage());
        if (!FileUtil.writeFile(coverPath, coverBytes)) {
            return RestResult.fail("000001", "封面图片写入失败");
        }

        VideoInfo videoInfo = optionalVideo.get();
        videoInfo.setCoverImageUrl(coverPath);
        videoInfo.setUpdateTime(LocalDateTime.now());
        videoInfoRepository.save(videoInfo);

        return RestResult.success();
    }

    @Override
    public RestResult<Void> frameCover(FrameCoverRequest request) {
        // 验证视频是否存在
        Optional<VideoInfo> optionalVideo = videoInfoRepository.findById(request.getVideoId());
        if (!optionalVideo.isPresent()) {
            return RestResult.fail("000001", "视频不存在");
        }

        // 截取指定时间点的视频帧作为封面图
        String coverPath = Paths.get(uploadPath, "covers", request.getVideoId() + "_frame.jpg").toString();
        if (!VideoUtil.extractFrameAsCover(optionalVideo.get().getOriginalFilePath(),
                request.getFrameTimeSecond(), coverPath)) {
            return RestResult.fail("000001", "提取视频帧失败");
        }

        // 保存截图并更新视频信息中的封面URL
        VideoInfo videoInfo = optionalVideo.get();
        videoInfo.setCoverImageUrl(coverPath);
        videoInfo.setUpdateTime(LocalDateTime.now());
        videoInfoRepository.save(videoInfo);

        return RestResult.success();
    }

    /**
     * 创建转码任务
     *
     * @param videoId 视频ID
     */
    private void createTranscodeTasks(Long videoId) {
        List<String> resolutions = Arrays.asList("360p", "720p", "1080p");
        for (String resolution : resolutions) {
            VideoTranscodeTask task = new VideoTranscodeTask();
            task.setVideoId(videoId);
            task.setResolution(resolution);
            task.setEncodingFormat("h264");
            task.setTaskStatus(0); // 等待状态
            task.setCreateTime(LocalDateTime.now());
            task.setUpdateTime(LocalDateTime.now());
            videoTranscodeTaskRepository.save(task);
        }
    }
}