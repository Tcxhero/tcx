package com.feisuanyz.common.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   敏感词查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordQuery {

    /**
     * 词类型: 1-弹幕 2-评论 3-标题
     */
    private Integer wordType;

    /**
     * 状态: 1-启用 2-禁用
     */
    private Integer status;

    /**
     * 页码，默认1
     */
    private Integer pageNo = 1;

    /**
     * 页面大小，默认10
     */
    private Integer pageSize = 10;
}