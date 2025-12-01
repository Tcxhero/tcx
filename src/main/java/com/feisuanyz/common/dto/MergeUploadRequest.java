package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 完成分片上传合并请求DTO
 *
 * @author tianchunxu
 */
@Data
public class MergeUploadRequest {

    /**
     * 上传任务ID
     */
    @NotBlank(message = "上传任务ID不能为空")
    private String uploadId;
}