package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   评论数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class CommentDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    private Long parentId;

    private Long replyToId;

    @NotBlank(message = "评论内容不能为空")
    private String content;
}