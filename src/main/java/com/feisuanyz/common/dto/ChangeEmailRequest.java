package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改邮箱请求对象
 */
@Data
public class ChangeEmailRequest {
    
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    /**
     * 新邮箱地址
     */
    @NotBlank(message = "新邮箱地址不能为空")
    private String newEmail;
    
    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
}