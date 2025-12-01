package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.VideoFullscreenDTO;
import com.feisuanyz.common.dto.VideoMuteDTO;
import com.feisuanyz.common.dto.VideoPlaybackSpeedDTO;
import com.feisuanyz.common.dto.VideoResolutionDTO;
import com.feisuanyz.common.dto.VideoSeekDTO;
import com.feisuanyz.common.response.RestResult;

/**
 * <p>
 *   视频播放控制管理的Service接口
 * </p>
 * @author tianchunxu
 */
public interface VideoControlService {

    RestResult setPlaybackSpeed(VideoPlaybackSpeedDTO.VideoPlaybackSpeedRequest request);

    RestResult switchResolution(VideoResolutionDTO.VideoResolutionRequest request);

    RestResult setFullscreen(VideoFullscreenDTO.VideoFullscreenRequest request);

    RestResult setMute(VideoMuteDTO.VideoMuteRequest request);

    RestResult seekToTime(VideoSeekDTO.VideoSeekRequest request);
}