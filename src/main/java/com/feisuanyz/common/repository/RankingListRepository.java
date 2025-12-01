package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.RankingList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   排行榜数据访问接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface RankingListRepository extends JpaRepository<RankingList, Long> {

    /**
     * 根据榜单类型查询排行榜数据并按得分降序排列
     *
     * @param rankType 榜单类型
     * @param offset   偏移量
     * @param limit    限制条数
     * @return 排行榜列表
     */
    @Query("SELECT r FROM RankingList r WHERE r.rankType = :rankType ORDER BY r.score DESC")
    List<RankingList> findByRankTypeOrderByScoreDesc(@Param("rankType") Integer rankType, @Param("offset") int offset, @Param("limit") int limit);
}