package com.feisuanyz.common.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 *   待审核视频数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class PendingVideoDTO {
    private Long id;
    private Long uploaderId;
    private String title;
    private String description;
    private Long categoryId;
    private String coverImageUrl;
    private Integer durationSeconds;
    private Long fileSize;
    private String originalFilePath;
    private Integer transcodedStatus;
    private Integer visibility;
    private LocalDateTime publishTime;
    private LocalDateTime createTime;
}