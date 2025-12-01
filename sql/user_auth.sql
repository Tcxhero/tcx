CREATE TABLE user_auth (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    login_type TINYINT NOT NULL COMMENT '登录类型: 1-手机号验证码 2-邮箱密码 3-微信 4-QQ',
    identifier VARCHAR(128) NOT NULL COMMENT '唯一标识符（如手机号、邮箱）',
    credential VARCHAR(255) COMMENT '凭证（如密码、token）',
    create_by BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by BIGINT UNSIGNED DEFAULT NULL COMMENT '修改人',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_login_type (user_id, login_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户认证信息表';