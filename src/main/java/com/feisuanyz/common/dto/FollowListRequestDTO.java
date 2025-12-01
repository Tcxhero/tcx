package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   获取关注列表或粉丝列表请求DTO
 * </p>
 * @author tianchunxu
 */
@Data
public class FollowListRequestDTO {
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}