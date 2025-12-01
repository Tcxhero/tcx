package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.PendingVideoDTO;
import com.feisuanyz.common.dto.RestResult;
import com.feisuanyz.common.entity.AdminOperationLog;
import com.feisuanyz.common.entity.VideoInfo;
import com.feisuanyz.common.query.PendingVideoQuery;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.service.AdminOperationLogService;
import com.feisuanyz.common.service.PendingVideoService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   待审核视频业务逻辑层实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class PendingVideoServiceImpl implements PendingVideoService {
    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Autowired
    private AdminOperationLogService adminOperationLogService;

    @Override
    public RestResult<List<PendingVideoDTO>> getPendingVideoList(PendingVideoQuery query) {
        // 这里假设有一个方法用于校验管理员权限
        if (!isAdmin()) {
            return RestResult.failure("000001", "权限不足");
        }

        Pageable pageable = PageRequest.of(query.getPageNum() - 1, query.getPageSize());
        List<VideoInfo> pendingVideos = videoInfoRepository.findByTranscodedStatusAndVisibilityOrderByCreateTimeDesc(0, 1, pageable);

        List<PendingVideoDTO> pendingVideoDTOs = pendingVideos.stream()
                .map(videoInfo -> {
                    PendingVideoDTO dto = new PendingVideoDTO();
                    dto.setId(videoInfo.getId());
                    dto.setUploaderId(videoInfo.getUploaderId());
                    dto.setTitle(videoInfo.getTitle());
                    dto.setDescription(videoInfo.getDescription());
                    dto.setCategoryId(videoInfo.getCategoryId());
                    dto.setCoverImageUrl(videoInfo.getCoverImageUrl());
                    dto.setDurationSeconds(videoInfo.getDurationSeconds());
                    dto.setFileSize(videoInfo.getFileSize());
                    dto.setOriginalFilePath(videoInfo.getOriginalFilePath());
                    dto.setTranscodedStatus(videoInfo.getTranscodedStatus());
                    dto.setVisibility(videoInfo.getVisibility());
                    dto.setPublishTime(videoInfo.getPublishTime());
                    dto.setCreateTime(videoInfo.getCreateTime());
                    return dto;
                })
                .collect(Collectors.toList());

        return RestResult.success(pendingVideoDTOs);
    }

    @Override
    @Transactional
    public RestResult<String> approveVideo(Long videoId) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(videoId);
        if (videoInfoOptional.isEmpty()) {
            return RestResult.failure("000001", "视频不存在");
        }

        VideoInfo videoInfo = videoInfoOptional.get();
        videoInfo.setTranscodedStatus(2);
        videoInfoRepository.save(videoInfo);

        AdminOperationLog adminOperationLog = new AdminOperationLog();
        adminOperationLog.setOperatorId(getCurrentAdminId());
        adminOperationLog.setOperationModule("视频审核");
        adminOperationLog.setOperationType("审核通过");
        adminOperationLog.setTargetId(videoId);
        adminOperationLog.setDetailInfo("视频ID: " + videoId);
        adminOperationLog.setIpAddress(getClientIpAddress());
        adminOperationLog.setCreateBy(getCurrentAdminId());
        adminOperationLog.setCreateTime(LocalDateTime.now());
        adminOperationLog.setUpdateBy(getCurrentAdminId());
        adminOperationLog.setUpdateTime(LocalDateTime.now());

        adminOperationLogService.saveAdminOperationLog(adminOperationLog);

        return RestResult.success("审核通过");
    }

    @Override
    @Transactional
    public RestResult<String> rejectVideo(Long videoId, String reason) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(videoId);
        if (videoInfoOptional.isEmpty()) {
            return RestResult.failure("000001", "视频不存在");
        }

        VideoInfo videoInfo = videoInfoOptional.get();
        videoInfo.setTranscodedStatus(3);
        videoInfoRepository.save(videoInfo);

        AdminOperationLog adminOperationLog = new AdminOperationLog();
        adminOperationLog.setOperatorId(getCurrentAdminId());
        adminOperationLog.setOperationModule("视频审核");
        adminOperationLog.setOperationType("拒绝");
        adminOperationLog.setTargetId(videoId);
        adminOperationLog.setDetailInfo("视频ID: " + videoId + ", 拒绝原因: " + reason);
        adminOperationLog.setIpAddress(getClientIpAddress());
        adminOperationLog.setCreateBy(getCurrentAdminId());
        adminOperationLog.setCreateTime(LocalDateTime.now());
        adminOperationLog.setUpdateBy(getCurrentAdminId());
        adminOperationLog.setUpdateTime(LocalDateTime.now());

        adminOperationLogService.saveAdminOperationLog(adminOperationLog);

        return RestResult.success("拒绝成功");
    }

    @Override
    @Transactional
    public RestResult<String> takeDownVideo(Long videoId, String reason) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(videoId);
        if (videoInfoOptional.isEmpty()) {
            return RestResult.failure("000001", "视频不存在");
        }

        VideoInfo videoInfo = videoInfoOptional.get();
        videoInfo.setVisibility(3);
        videoInfoRepository.save(videoInfo);

        AdminOperationLog adminOperationLog = new AdminOperationLog();
        adminOperationLog.setOperatorId(getCurrentAdminId());
        adminOperationLog.setOperationModule("视频管理");
        adminOperationLog.setOperationType("下架");
        adminOperationLog.setTargetId(videoId);
        adminOperationLog.setDetailInfo("视频ID: " + videoId + ", 下架原因: " + reason);
        adminOperationLog.setIpAddress(getClientIpAddress());
        adminOperationLog.setCreateBy(getCurrentAdminId());
        adminOperationLog.setCreateTime(LocalDateTime.now());
        adminOperationLog.setUpdateBy(getCurrentAdminId());
        adminOperationLog.setUpdateTime(LocalDateTime.now());

        adminOperationLogService.saveAdminOperationLog(adminOperationLog);

        return RestResult.success("下架成功");
    }

    // 模拟获取当前管理员ID
    private Long getCurrentAdminId() {
        return 1L;
    }

    // 模拟获取客户端IP地址
    private String getClientIpAddress() {
        return "127.0.0.1";
    }

    // 模拟校验管理员权限
    private boolean isAdmin() {
        return true;
    }
}