
CREATE TABLE admin_operation_log (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    operator_id BIGINT UNSIGNED NOT NULL COMMENT '操作员ID',
    operation_module VARCHAR(50) NOT NULL COMMENT '操作模块',
    operation_type VARCHAR(20) NOT NULL COMMENT '操作类型',
    target_id BIGINT UNSIGNED COMMENT '目标ID',
    detail_info TEXT COMMENT '详细信息',
    ip_address VARCHAR(45) COMMENT 'IP地址',
    create_by BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by BIGINT UNSIGNED DEFAULT NULL COMMENT '修改人',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    INDEX idx_operator_module (operator_id, operation_module)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台操作日志表';