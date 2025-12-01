package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.Danmaku;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   弹幕数据访问接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface DanmakuRepository extends JpaRepository<Danmaku, Long> {

    /**
     * 根据视频ID和播放时间点区间查询弹幕
     * @param videoId 视频ID
     * @param startTime 起始播放时间点
     * @param endTime 结束播放时间点
     * @return 弹幕列表
     */
    @EntityGraph(attributePaths = {"sender"})
    List<Danmaku> findByVideoIdAndPlayTimeSecondBetween(Long videoId, Integer startTime, Integer endTime);
}