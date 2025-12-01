package com.feisuanyz.common.dto;

import java.util.Date;
import lombok.Data;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   视频信息数据传输对象
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoInfoDTO {

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

    private Date publishTime;

    private Date createTime;

    private List<String> tagNames;

    private Date updateTime;
}
