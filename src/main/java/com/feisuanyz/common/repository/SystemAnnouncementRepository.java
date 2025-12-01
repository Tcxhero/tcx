package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.SystemAnnouncement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统公告数据访问接口
 * @author tianchunxu
 */
@Repository
public interface SystemAnnouncementRepository extends JpaRepository<SystemAnnouncement, Long> {

    /**
     * 根据状态和时间范围查询公告列表
     * @param status 状态
     * @param now 当前时间
     * @return 公告列表
     */
    @Query("SELECT a FROM SystemAnnouncement a WHERE (:status IS NULL OR a.status = :status) AND a.startTime <= :now AND a.endTime >= :now")
    List<SystemAnnouncement> findByStatusAndTimeRange(@Param("status") Integer status, @Param("now") LocalDateTime now);

    /**
     * 根据状态查询公告列表
     * @param status 状态
     * @return 公告列表
     */
    List<SystemAnnouncement> findByStatus(Integer status);
}