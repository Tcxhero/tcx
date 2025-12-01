package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.DynamicFeed;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 *   动态信息流表数据访问层
 * </p>
 * @author tianchunxu
 */
public interface DynamicFeedRepository extends JpaRepository<DynamicFeed, Long> {

    @Query("SELECT df FROM DynamicFeed df WHERE df.userId IN :followedIds ORDER BY df.createTime DESC")
    List<DynamicFeed> findByUserIdInOrderByCreateTimeDesc(@Param("followedIds") List<Long> followedIds, Pageable pageable);
}