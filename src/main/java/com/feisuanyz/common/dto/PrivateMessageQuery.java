package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   私信消息查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateMessageQuery {

    @NotNull(message = "目标用户ID不能为空")
    private Long targetUserId;

    private Integer pageSize = 20;

    private Integer pageNum = 1;
}