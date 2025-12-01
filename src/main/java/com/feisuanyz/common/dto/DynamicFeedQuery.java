package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   动态流查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
public class DynamicFeedQuery {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer pageSize;

    private Long lastId;
}