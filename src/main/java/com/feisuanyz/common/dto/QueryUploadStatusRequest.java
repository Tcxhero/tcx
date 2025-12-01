package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 查询视频上传状态请求DTO
 *
 * @author tianchunxu
 */
@Data
public class QueryUploadStatusRequest {

    /**
     * 上传任务ID
     */
    @NotBlank(message = "上传任务ID不能为空")
    private String uploadId;
}