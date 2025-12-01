package com.feisuanyz.controller;

import com.feisuanyz.common.dto.RestResult;
import com.feisuanyz.common.dto.UserAuthLoginRequest;
import com.feisuanyz.common.dto.UserAuthRegisterRequest;
import com.feisuanyz.common.dto.UserTokenValidateRequest;
import com.feisuanyz.common.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户认证控制器
 *
 * @author tianchunxu
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    /**
     * 用户注册接口
     *
     * @param request 注册请求参数
     * @return RestResult
     */
    @PostMapping("/register")
    public RestResult<?> register(@Valid @RequestBody UserAuthRegisterRequest request) {
        return userAuthService.register(request);
    }

    /**
     * 用户登录接口
     *
     * @param request 登录请求参数
     * @return RestResult
     */
    @PostMapping("/login")
    public RestResult<?> login(@Valid @RequestBody UserAuthLoginRequest request) {
        return userAuthService.login(request);
    }

    /**
     * JWT Token校验接口
     *
     * @param request 校验请求参数
     * @return RestResult
     */
    @PostMapping("/validateToken")
    public RestResult<?> validateToken(@Valid @RequestBody UserTokenValidateRequest request) {
        return userAuthService.validateToken(request);
    }
}