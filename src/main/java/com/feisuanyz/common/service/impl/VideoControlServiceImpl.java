package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.ErrorCode;
import com.feisuanyz.common.dto.VideoFullscreenDTO;
import com.feisuanyz.common.dto.VideoMuteDTO;
import com.feisuanyz.common.dto.VideoPlaybackSpeedDTO;
import com.feisuanyz.common.dto.VideoResolutionDTO;
import com.feisuanyz.common.dto.VideoSeekDTO;
import com.feisuanyz.common.entity.VideoInfo;
import com.feisuanyz.common.entity.VideoTranscodeTask;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.repository.VideoTranscodeTaskRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoControlService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   视频播放控制管理的Service实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class VideoControlServiceImpl implements VideoControlService {

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Autowired
    private VideoTranscodeTaskRepository videoTranscodeTaskRepository;

    @Override
    @Transactional
    public RestResult setPlaybackSpeed(VideoPlaybackSpeedDTO.VideoPlaybackSpeedRequest request) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(request.getVideoId());
        if (!videoInfoOptional.isPresent()) {
            return RestResult.error(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        // 这里可以添加记录用户设置的播放倍速的逻辑
        log.info("用户设置视频ID: {} 的播放倍速为: {}", request.getVideoId(), request.getPlaybackSpeed());

        return RestResult.success("调用成功");
    }

    @Override
    @Transactional
    public RestResult switchResolution(VideoResolutionDTO.VideoResolutionRequest request) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(request.getVideoId());
        if (!videoInfoOptional.isPresent()) {
            return RestResult.error(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        Optional<VideoTranscodeTask> taskOptional = videoTranscodeTaskRepository.findByVideoIdAndResolution(request.getVideoId(), request.getResolution());
        if (!taskOptional.isPresent() || taskOptional.get().getTaskStatus() != 2) {
            return RestResult.error(ErrorCode.RESOLUTION_UNAVAILABLE, "该分辨率不可用");
        }

        // 这里可以添加更新当前播放画质设置的逻辑
        log.info("用户切换视频ID: {} 的画质为: {}", request.getVideoId(), request.getResolution());

        return RestResult.success("调用成功");
    }

    @Override
    @Transactional
    public RestResult setFullscreen(VideoFullscreenDTO.VideoFullscreenRequest request) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(request.getVideoId());
        if (!videoInfoOptional.isPresent()) {
            return RestResult.error(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        // 这里可以添加设置播放器全屏状态的逻辑
        log.info("用户设置视频ID: {} 的全屏状态为: {}", request.getVideoId(), request.getIsFullscreen());

        return RestResult.success("调用成功");
    }

    @Override
    @Transactional
    public RestResult setMute(VideoMuteDTO.VideoMuteRequest request) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(request.getVideoId());
        if (!videoInfoOptional.isPresent()) {
            return RestResult.error(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        // 这里可以添加设置播放器静音状态的逻辑
        log.info("用户设置视频ID: {} 的静音状态为: {}", request.getVideoId(), request.getIsMuted());

        return RestResult.success("调用成功");
    }

    @Override
    @Transactional
    public RestResult seekToTime(VideoSeekDTO.VideoSeekRequest request) {
        Optional<VideoInfo> videoInfoOptional = videoInfoRepository.findById(request.getVideoId());
        if (!videoInfoOptional.isPresent()) {
            return RestResult.error(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        VideoInfo videoInfo = videoInfoOptional.get();
        if (request.getTargetTimeSecond() > videoInfo.getDurationSeconds()) {
            return RestResult.error(ErrorCode.INVALID_SEEK_TIME, "播放时间点非法");
        }

        // 这里可以添加跳转至指定播放时间点的逻辑
        log.info("用户设置视频ID: {} 的播放时间点为: {}", request.getVideoId(), request.getTargetTimeSecond());

        return RestResult.success("调用成功");
    }
}