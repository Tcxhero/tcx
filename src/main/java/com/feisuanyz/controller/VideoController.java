package com.feisuanyz.controller;

import com.feisuanyz.common.dto.VideoPublishRequestDTO;
import com.feisuanyz.common.dto.VideoPublishResponseDTO;
import com.feisuanyz.common.dto.VideoQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.feisuanyz.common.query.VideoQuery;
import com.feisuanyz.common.query.EditVideoQuery;
import com.feisuanyz.common.query.VisibilityQuery;
import com.feisuanyz.common.dto.VideoInfoDTO;

/**
 * <p>
 *   视频内容发布管理Controller
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/publish")
    public RestResult<VideoPublishResponseDTO> publishVideo(@Valid @RequestBody VideoPublishRequestDTO requestDTO) {
        VideoPublishResponseDTO responseDTO = videoService.publishVideo(requestDTO);
        return RestResult.success(responseDTO);
    }

    @GetMapping("/details")
    public RestResult<VideoPublishResponseDTO> getVideoPublishDetails(@Validated @ModelAttribute VideoQuery query) {
        VideoPublishResponseDTO responseDTO = videoService.getVideoPublishDetails(query);
        return RestResult.success(responseDTO);
    }

    @GetMapping("/list")
    public RestResult<List<VideoInfoDTO>> getPublishedVideoList(@Validated VideoQuery query) {
        return videoService.getPublishedVideoList(query);
    }

    @GetMapping("/detail/{videoId}")
    public RestResult<VideoInfoDTO> getVideoDetail(@PathVariable Long videoId) {
        return videoService.getVideoDetail(videoId);
    }

    @PutMapping("/edit")
    public RestResult<Void> editVideoInfo(@Validated @RequestBody EditVideoQuery query) {
        return videoService.editVideoInfo(query);
    }

    @DeleteMapping("/delete/{videoId}")
    public RestResult<Void> deleteVideo(@PathVariable Long videoId) {
        return videoService.deleteVideo(videoId);
    }

    @PutMapping("/visibility")
    public RestResult<Void> setVideoVisibility(@Validated @RequestBody VisibilityQuery query) {
        return videoService.setVideoVisibility(query);
    }
}
