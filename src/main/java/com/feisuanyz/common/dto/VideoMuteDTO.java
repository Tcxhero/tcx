package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   控制视频静音状态的DTO
 * </p>
 * @author tianchunxu
 */
@Data
public class VideoMuteDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotNull(message = "是否静音不能为空")
    private Boolean isMuted;
}