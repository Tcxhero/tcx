package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   关注用户请求DTO
 * </p>
 * @author tianchunxu
 */
@Data
public class FollowRequestDTO {
    @NotNull(message = "关注者ID不能为空")
    private Long followerId;

    @NotNull(message = "被关注者ID不能为空")
    private Long followedId;
}