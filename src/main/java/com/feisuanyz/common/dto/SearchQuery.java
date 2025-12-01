package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 *   搜索查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
public class SearchQuery {
    @NotBlank(message = "搜索关键词不能为空")
    private String keyword;

    @Positive(message = "页码必须为正整数")
    private int pageNo;

    @Positive(message = "每页数量必须为正整数")
    private int pageSize;

    private Date publishTimeStart;
    private Date publishTimeEnd;
    private int minDuration;
    private int maxDuration;
    private Long categoryId;
}