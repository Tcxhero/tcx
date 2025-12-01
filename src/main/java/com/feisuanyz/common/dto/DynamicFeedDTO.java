package com.feisuanyz.common.dto;

import lombok.Data;

/**
 * <p>
 *   动态流数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class DynamicFeedDTO {

    private Long id;
    private Long userId;
    private Integer actionType;
    private Long targetId;
    private Long createBy;
    private String createTime;
}