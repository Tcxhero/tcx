package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 视频基本信息实体类
 *
 * @author tianchunxu
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_info")
@Data
public class VideoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uploader_id", nullable = false)
    private Long uploaderId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "cover_image_url", length = 255)
    private String coverImageUrl;

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "original_file_path", length = 255)
    private String originalFilePath;

    @Column(name = "transcoded_status", nullable = false)
    private Integer transcodedStatus;

    @Column(name = "visibility", nullable = false)
    private Integer visibility;

    @Column(name = "publish_time")
    private LocalDateTime publishTime;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}
