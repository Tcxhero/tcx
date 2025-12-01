package com.feisuanyz.common.dto;

import java.util.List;
import lombok.Data;

/**
 * 查询视频上传状态响应DTO
 *
 * @author tianchunxu
 */
@Data
public class QueryUploadStatusResponse {

    /**
     * 已上传的分片索引列表
     */
    private List<Integer> uploadedChunks;

    /**
     * 总分片数
     */
    private Integer totalChunks;
}