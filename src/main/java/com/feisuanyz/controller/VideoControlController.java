package com.feisuanyz.controller;

import com.feisuanyz.common.dto.VideoFullscreenDTO;
import com.feisuanyz.common.dto.VideoMuteDTO;
import com.feisuanyz.common.dto.VideoPlaybackSpeedDTO;
import com.feisuanyz.common.dto.VideoResolutionDTO;
import com.feisuanyz.common.dto.VideoSeekDTO;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoControlService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *   视频播放控制管理的Controller
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/video/control")
@Validated
public class VideoControlController {

    @Autowired
    private VideoControlService videoControlService;

    @PostMapping("/playback-speed")
    public RestResult setPlaybackSpeed(@Valid @RequestBody VideoPlaybackSpeedDTO.VideoPlaybackSpeedRequest request) {
        return videoControlService.setPlaybackSpeed(request);
    }

    @PostMapping("/resolution")
    public RestResult switchResolution(@Valid @RequestBody VideoResolutionDTO.VideoResolutionRequest request) {
        return videoControlService.switchResolution(request);
    }

    @PostMapping("/fullscreen")
    public RestResult setFullscreen(@Valid @RequestBody VideoFullscreenDTO.VideoFullscreenRequest request) {
        return videoControlService.setFullscreen(request);
    }

    @PostMapping("/mute")
    public RestResult setMute(@Valid @RequestBody VideoMuteDTO.VideoMuteRequest request) {
        return videoControlService.setMute(request);
    }

    @PostMapping("/seek")
    public RestResult seekToTime(@Valid @RequestBody VideoSeekDTO.VideoSeekRequest request) {
        return videoControlService.seekToTime(request);
    }
}