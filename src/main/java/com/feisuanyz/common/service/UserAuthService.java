package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.RestResult;
import com.feisuanyz.common.dto.UserAuthLoginRequest;
import com.feisuanyz.common.dto.UserAuthRegisterRequest;
import com.feisuanyz.common.dto.UserTokenValidateRequest;

/**
 * 用户认证服务接口
 *
 * @author tianchunxu
 */
public interface UserAuthService {

    /**
     * 用户注册
     *
     * @param request 注册请求参数
     * @return RestResult
     */
    RestResult<?> register(UserAuthRegisterRequest request);

    /**
     * 用户登录
     *
     * @param request 登录请求参数
     * @return RestResult
     */
    RestResult<?> login(UserAuthLoginRequest request);

    /**
     * JWT Token校验
     *
     * @param request 校验请求参数
     * @return RestResult
     */
    RestResult<?> validateToken(UserTokenValidateRequest request);
}