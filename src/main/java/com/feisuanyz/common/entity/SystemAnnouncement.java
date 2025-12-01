package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统公告实体类
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_announcement")
public class SystemAnnouncement {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 公告标题
     */
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /**
     * 公告内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * 生效开始时间
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * 生效结束时间
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * 状态: 1-上线 2-下线
     */
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}