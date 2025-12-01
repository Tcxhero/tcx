package com.feisuanyz.common.dto;

import java.net.URL;
import java.time.LocalDate;
import lombok.Data;

/**
 * 用户信息数据传输对象
 */
@Data
public class UserInfoDTO {
    
    /**
     * 用户ID
     */
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
     * 性别: 0-未知 1-男 2-女
     */
    private Integer gender;
    
    /**
     * 生日
     */
    private LocalDate birthday;
    
    /**
     * 个人简介
     */
    private String bio;
}