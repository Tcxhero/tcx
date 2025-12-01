
CREATE TABLE video_comment (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    video_id BIGINT UNSIGNED NOT NULL COMMENT '视频ID',
    parent_id BIGINT UNSIGNED DEFAULT 0 COMMENT '父评论ID（0表示顶级评论）',
    reply_to_id BIGINT UNSIGNED DEFAULT 0 COMMENT '回复目标评论ID（0表示非回复）',
    commenter_id BIGINT UNSIGNED NOT NULL COMMENT '评论者ID',
    content TEXT NOT NULL COMMENT '评论内容',
    like_count INT DEFAULT 0 COMMENT '点赞数',
    floor_number INT DEFAULT 0 COMMENT '楼层号',
    depth_level TINYINT DEFAULT 1 COMMENT '层级深度（最多3层）',
    status TINYINT DEFAULT 1 COMMENT '状态: 1-正常 2-删除',
    create_by BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by BIGINT UNSIGNED DEFAULT NULL COMMENT '修改人',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    INDEX idx_video_parent_reply (video_id, parent_id, reply_to_id),
    INDEX idx_commenter (commenter_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频评论表';