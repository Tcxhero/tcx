package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 视频转码任务实体类
 *
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "video_transcode_task")
@AllArgsConstructor
@Data
public class VideoTranscodeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_id", nullable = false)
    private Long videoId;

    @Column(name = "resolution", nullable = false, length = 10)
    private String resolution;

    @Column(name = "encoding_format", length = 10)
    private String encodingFormat;

    @Column(name = "output_path", length = 255)
    private String outputPath;

    @Column(name = "task_status", nullable = false)
    private Integer taskStatus;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
