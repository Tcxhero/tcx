package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoUploadChunk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 视频上传分片记录Repository接口
 *
 * @author tianchunxu
 */
@Repository
public interface VideoUploadChunkRepository extends JpaRepository<VideoUploadChunk, Long> {

    /**
     * 根据上传任务ID查找所有分片记录
     *
     * @param uploadId 上传任务ID
     * @return 分片记录列表
     */
    List<VideoUploadChunk> findByUploadIdOrderByChunkIndexAsc(String uploadId);

    /**
     * 根据上传任务ID和分片索引查找分片记录
     *
     * @param uploadId 上传任务ID
     * @param chunkIndex 分片索引
     * @return 分片记录
     */
    VideoUploadChunk findByUploadIdAndChunkIndex(String uploadId, Integer chunkIndex);

    /**
     * 根据上传任务ID删除所有分片记录
     *
     * @param uploadId 上传任务ID
     */
    void deleteByUploadId(String uploadId);
}