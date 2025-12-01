package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频上传分片记录实体类
 *
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_upload_chunk")
public class VideoUploadChunk {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 上传任务ID
     */
    @Column(name = "upload_id", nullable = false)
    private String uploadId;

    /**
     * 分片索引
     */
    @Column(name = "chunk_index", nullable = false)
    private Integer chunkIndex;

    /**
     * 分片大小
     */
    @Column(name = "chunk_size", nullable = false)
    private Long chunkSize;

    /**
     * 分片存储路径
     */
    @Column(name = "chunk_path", nullable = false, length = 255)
    private String chunkPath;

    /**
     * MD5校验值
     */
    @Column(name = "md5_checksum", nullable = false, length = 32)
    private String md5Checksum;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private java.time.LocalDateTime createTime;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false)
    private java.time.LocalDateTime updateTime;
}