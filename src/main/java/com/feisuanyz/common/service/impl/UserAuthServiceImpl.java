package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.LoginTypeConstant;
import com.feisuanyz.common.dto.RestResult;
import com.feisuanyz.common.dto.UserAuthLoginRequest;
import com.feisuanyz.common.dto.UserAuthRegisterRequest;
import com.feisuanyz.common.dto.UserTokenValidateRequest;
import com.feisuanyz.common.entity.UserAuth;
import com.feisuanyz.common.entity.UserProfile;
import com.feisuanyz.common.repository.UserAuthRepository;
import com.feisuanyz.common.repository.UserProfileRepository;
import com.feisuanyz.common.service.UserAuthService;
import com.feisuanyz.common.util.JwtUtil;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户认证服务实现类
 *
 * @author tianchunxu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final UserProfileRepository userProfileRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public RestResult<?> register(UserAuthRegisterRequest request) {
        log.info("开始注册用户，请求参数: {}", request);

        // 校验登录类型是否合法
        if (!isValidLoginType(request.getLoginType())) {
            return RestResult.fail("000001", "登录类型不合法");
        }

        // 校验identifier是否已存在
        if (userAuthRepository.existsByLoginTypeAndIdentifier(request.getLoginType(), request.getIdentifier())) {
            return RestResult.fail("000001", "该identifier已被注册");
        }

        // 创建新的用户认证信息
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(System.currentTimeMillis()); // 简单模拟生成用户ID
        userAuth.setLoginType(request.getLoginType());
        userAuth.setIdentifier(request.getIdentifier());
        userAuth.setCredential(request.getCredential());
        userAuth.setCreateTime(LocalDateTime.now());
        userAuth.setUpdateTime(LocalDateTime.now());

        userAuthRepository.save(userAuth);

        // 初始化用户个人信息
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userAuth.getUserId());
        userProfile.setNickname("默认昵称");
        userProfile.setStatus(1); // 默认正常状态
        userProfile.setCreateTime(LocalDateTime.now());
        userProfile.setUpdateTime(LocalDateTime.now());

        userProfileRepository.save(userProfile);

        log.info("用户注册成功，用户ID: {}", userAuth.getUserId());
        return RestResult.success("注册成功");
    }

    @Override
    public RestResult<?> login(UserAuthLoginRequest request) {
        log.info("开始用户登录，请求参数: {}", request);

        // 查询是否存在匹配的用户认证信息
        Optional<UserAuth> optionalUserAuth = userAuthRepository.findByLoginTypeAndIdentifier(
                request.getLoginType(), request.getIdentifier());

        if (optionalUserAuth.isEmpty()) {
            return RestResult.fail("000001", "用户不存在或凭证错误");
        }

        UserAuth userAuth = optionalUserAuth.get();

        // 验证凭证有效性
        if (!request.getCredential().equals(userAuth.getCredential())) {
            return RestResult.fail("000001", "凭证无效");
        }

        // 签发JWT Token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userAuth.getUserId());
        String token = jwtUtil.generateToken(claims);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);

        log.info("用户登录成功，用户ID: {}", userAuth.getUserId());
        return RestResult.success(data);
    }

    @Override
    public RestResult<?> validateToken(UserTokenValidateRequest request) {
        log.info("开始校验JWT Token，请求参数: {}", request);

        try {
            if (!jwtUtil.validateToken(request.getToken())) {
                return RestResult.fail("000001", "Token无效或已过期");
            }

            Map<String, Object> claims = jwtUtil.getAllClaimsFromToken(request.getToken());
            Map<String, Object> data = new HashMap<>();
            data.put("userId", claims.get("userId"));
            data.put("expireTime", jwtUtil.getExpirationDateFromToken(request.getToken()));

            return RestResult.success(data);
        } catch (Exception e) {
            log.error("Token校验异常", e);
            return RestResult.fail("000001", "Token校验失败");
        }
    }

    /**
     * 判断登录类型是否合法
     *
     * @param loginType 登录类型
     * @return boolean
     */
    private boolean isValidLoginType(Integer loginType) {
        return loginType != null &&
                (loginType == LoginTypeConstant.PHONE_CODE ||
                        loginType == LoginTypeConstant.EMAIL_PASSWORD ||
                        loginType == LoginTypeConstant.WECHAT ||
                        loginType == LoginTypeConstant.QQ);
    }
}