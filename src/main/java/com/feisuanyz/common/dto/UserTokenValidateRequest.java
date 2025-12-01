package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * JWT Token校验请求参数
 *
 * @author tianchunxu
 */
@Data
public class UserTokenValidateRequest {

    /**
     * JWT Token字符串
     */
    @NotBlank(message = "Token不能为空")
    private String token;
}