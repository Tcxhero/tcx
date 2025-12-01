package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.VideoInteractionDTO;
import com.feisuanyz.common.dto.VideoInteractionQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoInteractionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *   视频互动行为控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/video-interaction")
@Validated
public class VideoInteractionController {

    @Autowired
    private VideoInteractionService videoInteractionService;

    @PostMapping("/like")
    public RestResult likeVideo(@Valid @RequestBody VideoInteractionQuery query) {
        return videoInteractionService.likeVideo(query);
    }

    @PostMapping("/unlike")
    public RestResult unlikeVideo(@Valid @RequestBody VideoInteractionQuery query) {
        return videoInteractionService.unlikeVideo(query);
    }

    @PostMapping("/coin")
    public RestResult coinVideo(@Valid @RequestBody VideoInteractionDTO dto) {
        return videoInteractionService.coinVideo(dto);
    }

    @PostMapping("/favorite")
    public RestResult favoriteVideo(@Valid @RequestBody VideoInteractionQuery query) {
        return videoInteractionService.favoriteVideo(query);
    }

    @PostMapping("/unfavorite")
    public RestResult unfavoriteVideo(@Valid @RequestBody VideoInteractionQuery query) {
        return videoInteractionService.unfavoriteVideo(query);
    }

    @PostMapping("/status")
    public RestResult getInteractionStatus(@Valid @RequestBody VideoInteractionQuery query) {
        return videoInteractionService.getInteractionStatus(query);
    }
}