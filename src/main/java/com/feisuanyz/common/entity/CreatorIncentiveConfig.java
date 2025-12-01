package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创作者激励预留配置实体类
 * 
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creator_incentive_config")
public class CreatorIncentiveConfig {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创作者ID
     */
    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    /**
     * 激励类型: 1-充电计划 2-广告分成
     */
    @Column(name = "incentive_type", nullable = false)
    private Integer incentiveType;

    /**
     * 状态: 0-未启用 1-已启用
     */
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private java.time.LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false)
    private java.time.LocalDateTime updateTime;
}