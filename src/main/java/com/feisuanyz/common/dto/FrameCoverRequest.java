package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 从视频帧选取封面请求DTO
 *
 * @author tianchunxu
 */
@Data
public class FrameCoverRequest {

    /**
     * 视频ID
     */
    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    /**
     * 截取帧的时间点，单位秒
     */
    @NotNull(message = "时间点不能为空")
    private Integer frameTimeSecond;
}