package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.*;
import com.feisuanyz.common.dto.ChangeEmailRequest;
import com.feisuanyz.common.dto.ChangePhoneRequest;
import com.feisuanyz.common.dto.DeviceListRequest;
import com.feisuanyz.common.dto.LogoutDeviceRequest;
import com.feisuanyz.common.dto.UpdateUserInfoRequest;
import com.feisuanyz.common.dto.UserInfoDTO;
import com.feisuanyz.common.entity.UserAuth;
import com.feisuanyz.common.entity.UserProfile;
import com.feisuanyz.common.repository.UserAuthRepository;
import com.feisuanyz.common.repository.UserProfileRepository;
import com.feisuanyz.common.service.UserProfileService;
import com.feisuanyz.common.util.RestResult;
import com.feisuanyz.common.util.RestResultUtil;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feisuanyz.common.util.BeanUtil;
import java.util.Date;
import com.feisuanyz.common.entity.AdminOperationLogDO;
import com.feisuanyz.common.query.UserProfileQuery;
import java.util.stream.Collectors;
import com.feisuanyz.common.exception.NoPermissionException;
import com.feisuanyz.common.service.AdminOperationLogService;
import java.util.List;
import com.feisuanyz.common.repository.AdminOperationLogRepository;
import com.feisuanyz.common.entity.UserProfileDO;
import com.feisuanyz.common.exception.UserNotFoundException;
import com.feisuanyz.common.constant.UserStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户个人信息服务实现类
 */
@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AdminOperationLogService adminOperationLogService;

    @Autowired
    private AdminOperationLogRepository adminOperationLogRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public RestResult<UserInfoDTO> getUserInfo(Long userId) {
        log.info("获取用户个人信息，userId={}", userId);
        // 查询用户个人信息
        Optional<UserProfile> profileOpt = userProfileRepository.findByUserId(userId);
        if (!profileOpt.isPresent()) {
            return RestResultUtil.error("用户信息不存在");
        }
        UserProfile profile = profileOpt.get();
        // 组装返回数据
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(profile.getUserId());
        userInfoDTO.setNickname(profile.getNickname());
        userInfoDTO.setAvatarUrl(profile.getAvatarUrl());
        userInfoDTO.setGender(profile.getGender());
        userInfoDTO.setBirthday(profile.getBirthday());
        userInfoDTO.setBio(profile.getBio());
        return RestResultUtil.success(userInfoDTO);
    }

    @Override
    public RestResult<Void> updateUserInfo(UpdateUserInfoRequest request) {
        log.info("更新用户个人信息，request={}", request);
        // 验证用户ID是否有效
        Optional<UserProfile> profileOpt = userProfileRepository.findByUserId(request.getUserId());
        if (!profileOpt.isPresent()) {
            return RestResultUtil.error("用户信息不存在");
        }
        UserProfile profile = profileOpt.get();
        // 更新用户个人信息
        if (request.getNickname() != null) {
            profile.setNickname(request.getNickname());
        }
        if (request.getAvatarUrl() != null) {
            profile.setAvatarUrl(request.getAvatarUrl());
        }
        if (request.getGender() != null) {
            profile.setGender(request.getGender());
        }
        if (request.getBirthday() != null) {
            profile.setBirthday(request.getBirthday());
        }
        if (request.getBio() != null) {
            profile.setBio(request.getBio());
        }
        profile.setUpdateTime(LocalDateTime.now());
        userProfileRepository.save(profile);
        return RestResultUtil.success();
    }

    @Override
    public RestResult<Void> changePhone(ChangePhoneRequest request) {
        log.info("修改绑定手机号，request={}", request);
        // 验证用户ID是否有效
        Optional<UserAuth> authOpt = userAuthRepository.findByUserIdAndLoginType(request.getUserId(), 1);
        if (!authOpt.isPresent()) {
            return RestResultUtil.error("用户信息不存在");
        }
        // 验证手机号验证码是否正确（这里简化处理，实际应调用验证码服务验证）
        if (!"123456".equals(request.getVerificationCode())) {
            return RestResultUtil.error("验证码错误");
        }
        // 更新用户绑定的手机号信息
        UserAuth userAuth = authOpt.get();
        userAuth.setIdentifier(request.getNewPhone());
        userAuth.setUpdateTime(LocalDateTime.now());
        userAuthRepository.save(userAuth);
        return RestResultUtil.success();
    }

    @Override
    public RestResult<Void> changeEmail(ChangeEmailRequest request) {
        log.info("修改绑定邮箱，request={}", request);
        // 验证用户ID是否有效
        Optional<UserAuth> authOpt = userAuthRepository.findByUserIdAndLoginType(request.getUserId(), 2);
        if (!authOpt.isPresent()) {
            return RestResultUtil.error("用户信息不存在");
        }
        // 验证用户输入的密码是否正确（这里简化处理，实际应进行密码校验）
        if (!"123456".equals(request.getPassword())) {
            return RestResultUtil.error("密码错误");
        }
        // 更新用户绑定的邮箱信息
        UserAuth userAuth = authOpt.get();
        userAuth.setIdentifier(request.getNewEmail());
        userAuth.setUpdateTime(LocalDateTime.now());
        userAuthRepository.save(userAuth);
        return RestResultUtil.success();
    }

    @Override
    public RestResult<Object> getDeviceList(DeviceListRequest request) {
        log.info("查询登录设备列表，request={}", request);
        // 根据用户ID查询该用户的所有登录设备记录
        // 这里模拟返回一个空列表
        return RestResultUtil.success(new Object[] {});
    }

    @Override
    public RestResult<Void> logoutDevice(LogoutDeviceRequest request) {
        log.info("远程登出指定设备，request={}", request);
        // 验证用户ID是否有效
        Optional<UserAuth> authOpt = userAuthRepository.findByUserIdAndLoginType(request.getUserId(), 1);
        if (!authOpt.isPresent()) {
            return RestResultUtil.error("用户信息不存在");
        }
        // 根据设备ID查找对应的登录设备记录（这里简化处理）
        if (request.getDeviceId() == null || request.getDeviceId() <= 0) {
            return RestResultUtil.error("设备信息不存在");
        }
        // 删除或标记该设备为已登出状态（这里简化处理）
        return RestResultUtil.success();
    }

    @Override
    public boolean checkUserExists(Long userId) {
        return userProfileRepository.existsByUserId(userId);
    }

    @Override
    @Transactional
    public UserProfileDTO banUser(Long operatorId, UserProfileQuery query, String reason) {
        validateAdminPermission(operatorId);
        UserProfileDO userProfileDO = userProfileRepository.findByUserId(query.getUserId()).orElseThrow(() -> new UserNotFoundException("用户不存在"));
        userProfileDO.setStatus(UserStatus.DISABLED.getStatus());
        userProfileDO.setUpdateBy(operatorId);
        userProfileDO.setUpdateTime(new Date());
        userProfileRepository.save(userProfileDO);
        adminOperationLogService.logOperation(operatorId, "UserProfile", "Ban", userProfileDO.getUserId(), reason, "127.0.0.1");
        return BeanUtil.copyProperties(userProfileDO, UserProfileDTO.class);
    }

    @Override
    @Transactional
    public UserProfileDTO unbanUser(Long operatorId, UserProfileQuery query, String reason) {
        validateAdminPermission(operatorId);
        UserProfileDO userProfileDO = userProfileRepository.findByUserId(query.getUserId()).orElseThrow(() -> new UserNotFoundException("用户不存在"));
        userProfileDO.setStatus(UserStatus.NORMAL.getStatus());
        userProfileDO.setUpdateBy(operatorId);
        userProfileDO.setUpdateTime(new Date());
        userProfileRepository.save(userProfileDO);
        adminOperationLogService.logOperation(operatorId, "UserProfile", "Unban", userProfileDO.getUserId(), reason, "127.0.0.1");
        return BeanUtil.copyProperties(userProfileDO, UserProfileDTO.class);
    }

    @Override
    public UserProfileDTO getUserProfile(Long userId) {
        UserProfileDO userProfileDO = userProfileRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("用户不存在"));
        UserProfileDTO userProfileDTO = BeanUtil.copyProperties(userProfileDO, UserProfileDTO.class);
        List<AdminOperationLogDO> logDOs = adminOperationLogRepository.findByTargetId(userId);
        userProfileDTO.setViolationLogs(logDOs.stream().map(logDO -> BeanUtil.copyProperties(logDO, AdminOperationLogDTO.class)).collect(Collectors.toList()));
        return userProfileDTO;
    }

    private void validateAdminPermission(Long operatorId) {
        // 这里假设有一个方法来校验管理员权限
        if (!isAdmin(operatorId)) {
            throw new NoPermissionException("权限不足");
        }
    }

    private boolean isAdmin(Long operatorId) {
        // 校验管理员权限的逻辑
        // 示例中返回true，实际应用中需要实现具体的校验逻辑
        return true;
    }
}
