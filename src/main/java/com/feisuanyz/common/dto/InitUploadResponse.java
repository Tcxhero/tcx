package com.feisuanyz.common.dto;

import lombok.Data;

/**
 * 初始化视频分片上传响应DTO
 *
 * @author tianchunxu
 */
@Data
public class InitUploadResponse {

    /**
     * 上传任务ID
     */
    private String uploadId;
}