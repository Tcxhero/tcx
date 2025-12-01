package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   后台操作日志表实体类
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "admin_operation_log")
@AllArgsConstructor
@Data
public class AdminOperationLog {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作员ID
     */
    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    /**
     * 操作模块
     */
    @Column(name = "operation_module", nullable = false, length = 50)
    private String operationModule;

    /**
     * 操作类型
     */
    @Column(name = "operation_type", nullable = false, length = 20)
    private String operationType;

    /**
     * 目标ID
     */
    @Column(name = "target_id")
    private Long targetId;

    /**
     * 详细信息
     */
    @Column(name = "detail_info", columnDefinition = "TEXT")
    private String detailInfo;

    /**
     * IP地址
     */
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

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
