
CREATE TABLE creator_dashboard_data (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    creator_id BIGINT UNSIGNED NOT NULL COMMENT '创作者ID',
    stat_date DATE NOT NULL COMMENT '统计数据日期',
    view_count INT DEFAULT 0 COMMENT '播放量',
    like_count INT DEFAULT 0 COMMENT '点赞数',
    coin_count INT DEFAULT 0 COMMENT '投币数',
    favorite_count INT DEFAULT 0 COMMENT '收藏数',
    fan_growth INT DEFAULT 0 COMMENT '粉丝增长数',
    region_distribution JSON COMMENT '地区分布数据',
    create_by BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by BIGINT UNSIGNED DEFAULT NULL COMMENT '修改人',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_creator_statdate (creator_id, stat_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='创作者数据看板表';