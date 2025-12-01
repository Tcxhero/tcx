package com.feisuanyz.common.constant;


/**
 * <p>
 *   用户状态常量类
 * </p>
 * @author tianchunxu
 */
public enum UserStatus {

    NORMAL(1, "正常"),
    DISABLED(2, "禁用");

    private final Integer status;
    private final String description;

    UserStatus(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}