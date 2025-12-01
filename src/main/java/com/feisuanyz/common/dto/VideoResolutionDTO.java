package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   切换视频画质的DTO
 * </p>
 * @author tianchunxu
 */
public class VideoResolutionDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotBlank(message = "目标分辨率不能为空")
    private String resolution;

    // Lombok 自动生成 getter 和 setter
    @lombok.Data
    public static class VideoResolutionRequest {
        @NotNull(message = "视频ID不能为空")
        private Long videoId;

        @NotBlank(message = "目标分辨率不能为空")
        private String resolution;
    }
}