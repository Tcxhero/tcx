
CREATE TABLE video_info (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    uploader_id BIGINT UNSIGNED NOT NULL COMMENT '上传者ID',
    title VARCHAR(50) NOT NULL COMMENT '视频标题',
    description TEXT COMMENT '视频简介',
    category_id BIGINT UNSIGNED NOT NULL COMMENT '分区ID',
    cover_image_url VARCHAR(255) COMMENT '封面图片URL',
    duration_seconds INT DEFAULT 0 COMMENT '视频时长（秒）',
    file_size BIGINT DEFAULT 0 COMMENT '原始文件大小',
    original_file_path VARCHAR(255) COMMENT '原始文件路径',
    transcoded_status TINYINT DEFAULT 0 COMMENT '转码状态: 0-未开始 1-转码中 2-已完成 3-失败',
    visibility TINYINT DEFAULT 1 COMMENT '可见性: 1-公开 2-仅自己可见 3-定时发布',
    publish_time DATETIME COMMENT '发布时间',
    create_by BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by BIGINT UNSIGNED DEFAULT NULL COMMENT '修改人',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    INDEX idx_uploader_id (uploader_id),
    INDEX idx_category_id (category_id),
    INDEX idx_publish_time (publish_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频基本信息表';