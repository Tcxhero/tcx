package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.RecommendedVideoDO;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   数据库访问与持久化操作
 * </p>
 * @author tianchunxu
 */
@Repository
public interface RecommendedVideoRepository extends JpaRepository<RecommendedVideoDO, Long> {

    @EntityGraph(attributePaths = {"videoId"})
    List<RecommendedVideoDO> findByUserId(Long userId);
}