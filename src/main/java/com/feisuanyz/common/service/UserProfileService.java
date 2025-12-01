package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.ChangeEmailRequest;
import com.feisuanyz.common.dto.ChangePhoneRequest;
import com.feisuanyz.common.dto.DeviceListRequest;
import com.feisuanyz.common.dto.LogoutDeviceRequest;
import com.feisuanyz.common.dto.UpdateUserInfoRequest;
import com.feisuanyz.common.dto.UserInfoDTO;
import com.feisuanyz.common.util.RestResult;
import com.feisuanyz.common.dto.UserProfileDTO;
import com.feisuanyz.common.query.UserProfileQuery;

/**
 * 用户个人信息服务接口
 */
public interface UserProfileService {

    /**
     * 获取用户个人信息
     * @param userId 用户ID
     * @return RestResult
     */
    RestResult<UserInfoDTO> getUserInfo(Long userId);

    /**
     * 修改用户个人信息
     * @param request 更新用户信息请求对象
     * @return RestResult
     */
    RestResult<Void> updateUserInfo(UpdateUserInfoRequest request);

    /**
     * 修改绑定手机号
     * @param request 修改手机号请求对象
     * @return RestResult
     */
    RestResult<Void> changePhone(ChangePhoneRequest request);

    /**
     * 修改绑定邮箱
     * @param request 修改邮箱请求对象
     * @return RestResult
     */
    RestResult<Void> changeEmail(ChangeEmailRequest request);

    /**
     * 查询登录设备列表
     * @param request 查询登录设备列表请求对象
     * @return RestResult
     */
    RestResult<Object> getDeviceList(DeviceListRequest request);

    /**
     * 远程登出指定设备
     * @param request 远程登出设备请求对象
     * @return RestResult
     */
    RestResult<Void> logoutDevice(LogoutDeviceRequest request);

    boolean checkUserExists(Long userId);

    UserProfileDTO banUser(Long operatorId, UserProfileQuery query, String reason);

    UserProfileDTO unbanUser(Long operatorId, UserProfileQuery query, String reason);

    UserProfileDTO getUserProfile(Long userId);
}
