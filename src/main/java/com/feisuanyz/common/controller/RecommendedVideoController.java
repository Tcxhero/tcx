package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.RecommendedVideoDTO;
import com.feisuanyz.common.query.RecommendedVideoQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.RecommendedVideoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   控制器类，处理 HTTP 请求与响应
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/recommended-video")
public class RecommendedVideoController {

    @Autowired
    private RecommendedVideoService recommendedVideoService;

    @GetMapping("/list")
    public RestResult<List<RecommendedVideoDTO>> getRecommendedVideoList(@Validated RecommendedVideoQuery query) {
        try {
            List<RecommendedVideoDTO> recommendedVideos = recommendedVideoService.getRecommendedVideoList(query);
            return RestResult.success(recommendedVideos);
        } catch (Exception e) {
            log.error("获取个性化推荐视频列表时发生异常", e);
            return RestResult.failure("系统异常");
        }
    }

    @PostMapping("/refresh")
    public RestResult<Void> refreshUserRecommendation(@RequestParam @NotNull Long userId) {
        try {
            recommendedVideoService.refreshUserRecommendation(userId);
            return RestResult.success();
        } catch (Exception e) {
            log.error("刷新用户个性化推荐时发生异常", e);
            return RestResult.failure("系统异常");
        }
    }

    @PostMapping("/clear")
    public RestResult<Void> clearUserRecommendation(@RequestParam @NotNull Long userId) {
        try {
            recommendedVideoService.clearUserRecommendation(userId);
            return RestResult.success();
        } catch (Exception e) {
            log.error("清除用户个性化推荐记录时发生异常", e);
            return RestResult.failure("系统异常");
        }
    }
}