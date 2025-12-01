
CREATE TABLE video_transcode_task (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    video_id BIGINT UNSIGNED NOT NULL COMMENT '视频ID',
    resolution VARCHAR(10) NOT NULL COMMENT '分辨率（如360p）',
    encoding_format VARCHAR(10) DEFAULT 'h264' COMMENT '编码格式',
    output_path VARCHAR(255) COMMENT '输出文件路径',
    task_status TINYINT DEFAULT 0 COMMENT '任务状态: 0-等待 1-处理中 2-成功 3-失败',
    error_message TEXT COMMENT '错误信息',
    create_by BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by BIGINT UNSIGNED DEFAULT NULL COMMENT '修改人',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_video_resolution (video_id, resolution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频转码任务表';