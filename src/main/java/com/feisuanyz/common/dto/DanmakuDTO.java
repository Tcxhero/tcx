package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   弹幕数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanmakuDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotBlank(message = "弹幕内容不能为空")
    private String content;

    @NotNull(message = "播放时间点不能为空")
    private Integer playTimeSecond;

    private String color = "#FFFFFF";

    private Integer position = 1;
}