package com.feisuanyz.controller;

import com.feisuanyz.common.dto.*;
import com.feisuanyz.common.dto.ChangeEmailRequest;
import com.feisuanyz.common.dto.ChangePhoneRequest;
import com.feisuanyz.common.dto.DeviceListRequest;
import com.feisuanyz.common.dto.LogoutDeviceRequest;
import com.feisuanyz.common.dto.UpdateUserInfoRequest;
import com.feisuanyz.common.service.UserProfileService;
import com.feisuanyz.common.util.RestResult;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户个人信息控制器
 */
@Slf4j
@RestController
@RequestMapping("/user-profile")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    /**
     * 获取用户个人信息
     * @param userId 用户ID
     * @return RestResult
     */
    @GetMapping("/info")
    public RestResult<?> getUserInfo(@RequestParam Long userId) {
        log.info("获取用户个人信息，userId={}", userId);
        return userProfileService.getUserInfo(userId);
    }
    
    /**
     * 修改用户个人信息
     * @param request 更新用户信息请求对象
     * @return RestResult
     */
    @PostMapping("/update-info")
    public RestResult<?> updateUserInfo(@RequestBody @Valid UpdateUserInfoRequest request) {
        log.info("修改用户个人信息，request={}", request);
        return userProfileService.updateUserInfo(request);
    }
    
    /**
     * 修改绑定手机号
     * @param request 修改手机号请求对象
     * @return RestResult
     */
    @PostMapping("/change-phone")
    public RestResult<?> changePhone(@RequestBody @Valid ChangePhoneRequest request) {
        log.info("修改绑定手机号，request={}", request);
        return userProfileService.changePhone(request);
    }
    
    /**
     * 修改绑定邮箱
     * @param request 修改邮箱请求对象
     * @return RestResult
     */
    @PostMapping("/change-email")
    public RestResult<?> changeEmail(@RequestBody @Valid ChangeEmailRequest request) {
        log.info("修改绑定邮箱，request={}", request);
        return userProfileService.changeEmail(request);
    }
    
    /**
     * 查询登录设备列表
     * @param request 查询登录设备列表请求对象
     * @return RestResult
     */
    @PostMapping("/device-list")
    public RestResult<?> getDeviceList(@RequestBody @Valid DeviceListRequest request) {
        log.info("查询登录设备列表，request={}", request);
        return userProfileService.getDeviceList(request);
    }
    
    /**
     * 远程登出指定设备
     * @param request 远程登出设备请求对象
     * @return RestResult
     */
    @PostMapping("/logout-device")
    public RestResult<?> logoutDevice(@RequestBody @Valid LogoutDeviceRequest request) {
        log.info("远程登出指定设备，request={}", request);
        return userProfileService.logoutDevice(request);
    }
}