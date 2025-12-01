package com.feisuanyz.common.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   敏感词数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 敏感词内容
     */
    private String wordContent;

    /**
     * 词类型: 1-弹幕 2-评论 3-标题
     */
    private Integer wordType;

    /**
     * 状态: 1-启用 2-禁用
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private java.time.LocalDateTime createTime;

    /**
     * 修改人
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    private java.time.LocalDateTime updateTime;
}