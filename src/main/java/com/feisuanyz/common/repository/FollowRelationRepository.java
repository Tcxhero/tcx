package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.FollowRelationDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.feisuanyz.common.entity.FollowRelation;

/**
 * <p>
 *   用户关注关系表Repository接口
 * </p>
 * @author tianchunxu
 */
public interface FollowRelationRepository extends JpaRepository<FollowRelationDO, Long> {

    boolean existsByFollowerIdAndFollowedId(Long followerId, Long followedId);

    void deleteByFollowerIdAndFollowedId(Long followerId, Long followedId);

    Page<FollowRelationDO> findByFollowerId(Long followerId, Pageable pageable);

    Page<FollowRelationDO> findByFollowedId(Long followedId, Pageable pageable);

    @Query("SELECT fr.followedId FROM FollowRelation fr WHERE fr.followerId = :followerId")
    List<Long> findFollowedIdsByFollowerId(@Param("followerId") Long followerId);
}
