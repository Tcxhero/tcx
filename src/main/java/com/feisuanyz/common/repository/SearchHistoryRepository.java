package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.SearchHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   搜索历史记录数据库访问接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findByUserIdOrderBySearchTimeDesc(Long userId);

    @Modifying
    @Query("DELETE FROM SearchHistory s WHERE s.userId = ?1")
    void deleteByUserId(Long userId);
}