package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   视频互动行为查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
public class VideoInteractionQuery {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "视频ID不能为空")
    private Long videoId;
}