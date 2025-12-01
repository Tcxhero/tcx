package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   后台操作日志实体类
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_operation_log")
public class AdminOperationLogDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @Column(name = "operation_module", nullable = false)
    private String operationModule;

    @Column(name = "operation_type", nullable = false)
    private String operationType;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "detail_info")
    private String detailInfo;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}