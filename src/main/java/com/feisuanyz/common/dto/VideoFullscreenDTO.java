package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   控制视频全屏模式的DTO
 * </p>
 * @author tianchunxu
 */
@Data
public class VideoFullscreenDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotNull(message = "是否全屏不能为空")
    private Boolean isFullscreen;
}