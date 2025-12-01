package com.feisuanyz.common.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 创作者激励预留配置DTO
 *
 * @author tianchunxu
 */
@Data
public class CreatorIncentiveConfigDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 创作者ID
     */
    private Long creatorId;

    /**
     * 激励类型: 1-充电计划 2-广告分成
     */
    private Integer incentiveType;

    /**
     * 状态: 0-未启用 1-已启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}