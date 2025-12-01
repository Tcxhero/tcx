package com.feisuanyz.common.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 创作者激励预留配置查询对象
 *
 * @author tianchunxu
 */
@Data
public class CreatorIncentiveConfigQuery {

    /**
     * 创作者ID
     */
    @NotNull(message = "创作者ID不能为空")
    private Long creatorId;

    /**
     * 激励类型: 1-充电计划 2-广告分成
     */
    @NotNull(message = "激励类型不能为空")
    private Integer incentiveType;

    /**
     * 状态: 0-未启用 1-已启用
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 配置ID
     */
    private Long configId;
}