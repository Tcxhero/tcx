package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   排行榜查询参数DTO
 * </p>
 * @author tianchunxu
 */
@Data
public class RankingListQuery {

    /**
     * 榜单类型: 1-日榜 2-周榜
     */
    @NotNull(message = "榜单类型不能为空")
    private Integer rankType;

    /**
     * 每页数量，默认10
     */
    private Integer pageSize = 10;

    /**
     * 页码，默认1
     */
    private Integer pageNum = 1;
}