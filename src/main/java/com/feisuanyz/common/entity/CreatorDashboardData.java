package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 *   创作者数据看板表实体类
 * </p>
 * @author tianchunxu
 */
@Entity
@Table(name = "creator_dashboard_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDashboardData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "stat_date", nullable = false)
    private LocalDate statDate;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "coin_count", nullable = false)
    private Integer coinCount;

    @Column(name = "favorite_count", nullable = false)
    private Integer favoriteCount;

    @Column(name = "fan_growth", nullable = false)
    private Integer fanGrowth;

    @Column(name = "region_distribution", columnDefinition = "json")
    private String regionDistribution;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}