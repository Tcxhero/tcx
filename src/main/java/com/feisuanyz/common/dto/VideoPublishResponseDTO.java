package com.feisuanyz.common.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   发布视频响应DTO
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPublishResponseDTO {

    private Long videoId;
    private String title;
    private String description;
    private Long categoryId;
    private List<String> tags;
    private Integer visibility;
    private Date publishTime;
}