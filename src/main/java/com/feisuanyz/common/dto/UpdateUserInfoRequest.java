package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import lombok.Data;

/**
 * 更新用户信息请求对象
 */
@Data
public class UpdateUserInfoRequest {
    
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 头像URL
     */
    private String avatarUrl;
    
    /**
     * 性别
     */
    private Integer gender;
    
    /**
     * 生日
     */
    private java.time.LocalDate birthday;
    
    /**
     * 个人简介
     */
    private String bio;
}