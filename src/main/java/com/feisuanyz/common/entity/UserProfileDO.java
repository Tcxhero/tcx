package com.feisuanyz.common.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   用户个人信息表实体类
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
@AllArgsConstructor
@Data
public class UserProfileDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "gender", columnDefinition = "TINYINT DEFAULT 0")
    private Integer gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "bio")
    private String bio;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private Integer status;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    // 添加字段存储相关的操作日志
    @OneToMany(mappedBy = "targetId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminOperationLogDO> violationLogs;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
