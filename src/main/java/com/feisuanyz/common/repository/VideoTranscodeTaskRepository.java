package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoTranscodeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.Optional;

/**
 * 视频转码任务Repository接口
 *
 * @author tianchunxu
 */
@Repository
public interface VideoTranscodeTaskRepository extends JpaRepository<VideoTranscodeTask, Long> {

    @EntityGraph(attributePaths = { "videoId", "resolution" })
    Optional<VideoTranscodeTask> findByVideoIdAndResolution(Long videoId, String resolution);
}
