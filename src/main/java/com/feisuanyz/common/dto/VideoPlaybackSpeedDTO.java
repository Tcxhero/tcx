package com.feisuanyz.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   视频播放倍速设置的DTO
 * </p>
 * @author tianchunxu
 */
public class VideoPlaybackSpeedDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotNull(message = "播放倍速不能为空")
    @Min(value = 0, message = "播放倍速最小为0.5")
    @Max(value = 2, message = "播放倍速最大为2.0")
    private Double playbackSpeed;

    // Lombok 自动生成 getter 和 setter
    @lombok.Data
    public static class VideoPlaybackSpeedRequest {
        @NotNull(message = "视频ID不能为空")
        private Long videoId;

        @NotNull(message = "播放倍速不能为空")
        @Min(value = 0, message = "播放倍速最小为0.5")
        @Max(value = 2, message = "播放倍速最大为2.0")
        private Double playbackSpeed;
    }
}