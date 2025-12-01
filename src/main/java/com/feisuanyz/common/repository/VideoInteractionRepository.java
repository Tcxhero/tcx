package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoInteractionDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   视频互动行为数据访问层
 * </p>
 * @author tianchunxu
 */
@Repository
public interface VideoInteractionRepository extends JpaRepository<VideoInteractionDO, Long> {

    @EntityGraph(attributePaths = { "user", "video" })
    Optional<VideoInteractionDO> findByUserIdAndVideoId(Long userId, Long videoId);

    List<VideoInteractionDO> findByUserId(Long userId);
}
