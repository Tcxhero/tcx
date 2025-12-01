package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户注册请求参数
 *
 * @author tianchunxu
 */
@Data
public class UserAuthRegisterRequest {

    /**
     * 登录类型
     */
    @NotNull(message = "登录类型不能为空")
    private Integer loginType;

    /**
     * 唯一标识符（如手机号、邮箱）
     */
    @NotBlank(message = "唯一标识符不能为空")
    private String identifier;

    /**
     * 凭证（如密码、token）
     */
    @NotBlank(message = "凭证不能为空")
    private String credential;
}