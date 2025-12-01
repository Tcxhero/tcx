package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 上传单个视频分片请求DTO
 *
 * @author tianchunxu
 */
@Data
public class UploadChunkRequest {

    /**
     * 上传任务ID
     */
    @NotBlank(message = "上传任务ID不能为空")
    private String uploadId;

    /**
     * 当前分片索引，从0开始
     */
    @NotNull(message = "分片索引不能为空")
    private Integer chunkIndex;

    /**
     * 分片二进制数据
     */
    @NotBlank(message = "分片数据不能为空")
    private String chunkData;

    /**
     * MD5校验值
     */
    @NotBlank(message = "MD5校验值不能为空")
    private String md5Checksum;
}