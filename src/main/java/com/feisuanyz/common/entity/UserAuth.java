package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户认证信息实体类
 *
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
@AllArgsConstructor
@Data
public class UserAuth {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 登录类型: 1-手机号验证码 2-邮箱密码 3-微信 4-QQ
     */
    @Column(name = "login_type", nullable = false)
    private Integer loginType;

    /**
     * 唯一标识符（如手机号、邮箱）
     */
    @Column(name = "identifier", nullable = false, length = 128)
    private String identifier;

    /**
     * 凭证（如密码、token）
     */
    @Column(name = "credential", length = 255)
    private String credential;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private java.time.LocalDateTime createTime;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false)
    private java.time.LocalDateTime updateTime;
}
