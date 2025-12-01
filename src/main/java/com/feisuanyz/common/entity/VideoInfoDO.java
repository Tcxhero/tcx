package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 *   视频基本信息实体类
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "video_info")
@AllArgsConstructor
@Data
public class VideoInfoDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uploader_id", nullable = false)
    private Long uploaderId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "original_file_path")
    private String originalFilePath;

    @Column(name = "transcoded_status")
    private Integer transcodedStatus;

    @Column(name = "visibility")
    private Integer visibility;

    @Column(name = "publish_time")
    private Date publishTime;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
