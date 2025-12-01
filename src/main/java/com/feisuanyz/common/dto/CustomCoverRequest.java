package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 自定义上传视频封面请求DTO
 *
 * @author tianchunxu
 */
@Data
public class CustomCoverRequest {

    /**
     * 视频ID
     */
    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    /**
     * 封面图片二进制数据
     */
    @NotBlank(message = "封面图片数据不能为空")
    private String coverImage;
}