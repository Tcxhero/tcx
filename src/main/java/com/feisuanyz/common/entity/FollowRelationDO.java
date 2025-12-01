package com.feisuanyz.common.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * <p>
 *   用户关注关系表实体类
 * </p>
 * @author tianchunxu
 */
@Data
@Entity
@Table(name = "follow_relation")
public class FollowRelationDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @Column(name = "followed_id", nullable = false)
    private Long followedId;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}