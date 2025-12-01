package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.CreatorDashboardData;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   创作者数据看板Repository接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface CreatorDashboardRepository extends JpaRepository<CreatorDashboardData, Long> {

    @EntityGraph(attributePaths = {"regionDistribution"})
    List<CreatorDashboardData> findByCreatorIdAndStatDateBetween(Long creatorId, LocalDate startDate, LocalDate endDate);
}