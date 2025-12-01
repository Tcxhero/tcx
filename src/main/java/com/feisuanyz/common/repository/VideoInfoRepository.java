package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import com.feisuanyz.common.entity.VideoInfoDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

/**
 * 视频基本信息Repository接口
 *
 * @author tianchunxu
 */
@Repository
public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {

    @EntityGraph(attributePaths = { "tags" })
    Optional<VideoInfoDO> findById(Long videoId);

    @Query("SELECT v FROM VideoInfo v WHERE (v.title LIKE %?1% OR v.description LIKE %?1%) " + "AND (?2 IS NULL OR v.publishTime >= ?2) " + "AND (?3 IS NULL OR v.publishTime <= ?3) " + "AND (?4 = 0 OR v.durationSeconds >= ?4) " + "AND (?5 = 0 OR v.durationSeconds <= ?5) " + "AND (?6 = 0 OR v.categoryId = ?6)")
    Page<VideoInfo> searchVideos(String keyword, Date publishTimeStart, Date publishTimeEnd, int minDuration, int maxDuration, Long categoryId, Pageable pageable);

    @EntityGraph(attributePaths = { "id", "title", "description", "coverImageUrl", "durationSeconds" })
    List<VideoInfoDO> findAllByOrderByTranscodedStatusDesc();

    @EntityGraph(attributePaths = { "videoTagMappings.tag" })
    List<VideoInfoDO> findByUploaderIdAndVisibility(Long uploaderId, Integer visibility, org.springframework.data.domain.Pageable pageable);

    @EntityGraph(attributePaths = { "videoTagMappings.tag" })
    VideoInfoDO findByIdAndVisibility(Long videoId, Integer visibility);

    @EntityGraph(attributePaths = { "uploaderId", "categoryId" })
    List<VideoInfo> findByTranscodedStatusAndVisibilityOrderByCreateTimeDesc(Integer transcodedStatus, Integer visibility, Pageable pageable);
}
