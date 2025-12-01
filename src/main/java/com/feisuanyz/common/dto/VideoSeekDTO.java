package com.feisuanyz.common.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   拖拽视频播放进度的DTO
 * </p>
 * @author tianchunxu
 */
public class VideoSeekDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotNull(message = "目标播放时间点不能为空")
    @Min(value = 0, message = "目标播放时间点不能为负数")
    private Integer targetTimeSecond;

    // Lombok 自动生成 getter 和 setter
    @lombok.Data
    public static class VideoSeekRequest {
        @NotNull(message = "视频ID不能为空")
        private Long videoId;

        @NotNull(message = "目标播放时间点不能为空")
        @Min(value = 0, message = "目标播放时间点不能为负数")
        private Integer targetTimeSecond;
    }
}