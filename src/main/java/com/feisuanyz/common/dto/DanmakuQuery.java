package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   弹幕查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanmakuQuery {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotNull(message = "起始播放时间点不能为空")
    private Integer startTime;

    @NotNull(message = "结束播放时间点不能为空")
    private Integer endTime;
}