package com.feisuanyz.common.query;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 *   用户查询参数封装对象
 * </p>
 * @author tianchunxu
 */
public class UserProfileQuery {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}