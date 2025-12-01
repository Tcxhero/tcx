package com.feisuanyz.common.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
public class RecommendedVideoQuery {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer pageSize = 10;

    private Integer pageNum = 1;
}