package com.feisuanyz.common.query;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * <p>
 *   创作者数据看板查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDashboardQuery {

    @NotNull(message = "创作者ID不能为空")
    private Long creatorId;

    @Min(value = 1, message = "统计天数必须大于0")
    @Max(value = 30, message = "统计天数不能超过30天")
    @PositiveOrZero
    private Integer days = 7;
}