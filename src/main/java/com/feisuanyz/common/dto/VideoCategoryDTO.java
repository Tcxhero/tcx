package com.feisuanyz.common.dto;

import lombok.Data;

/**
 * <p>
 *   视频分类数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class VideoCategoryDTO {
    private Long id;
    private String categoryName;
    private Integer sortOrder;
    private Long createBy;
    private String createTime;
    private Long updateBy;
    private String updateTime;
}