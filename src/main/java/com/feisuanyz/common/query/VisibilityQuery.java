package com.feisuanyz.common.query;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   调整视频可见性查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisibilityQuery {
    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotNull(message = "可见性设置不能为空")
    @Min(value = 1, message = "可见性设置必须大于等于1")
    private Integer visibility;
}