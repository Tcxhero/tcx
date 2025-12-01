package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 *   搜索历史记录实体对象
 * </p>
 * @author tianchunxu
 */
@Data
@Entity
@Table(name = "search_history")
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "search_time", nullable = false)
    private Date searchTime;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}