package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 初始化视频分片上传请求DTO
 *
 * @author tianchunxu
 */
@Data
public class InitUploadRequest {

    /**
     * 文件名
     */
    @NotBlank(message = "文件名不能为空")
    private String fileName;

    /**
     * 文件总大小，单位字节
     */
    @NotNull(message = "文件大小不能为空")
    private Long fileSize;

    /**
     * 每个分片大小，单位字节
     */
    @NotNull(message = "分片大小不能为空")
    private Long chunkSize;
}