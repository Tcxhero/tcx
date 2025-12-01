package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.RankingListQuery;
import com.feisuanyz.common.dto.RankingListVO;
import com.feisuanyz.common.response.RestResult;
import java.util.List;

/**
 * <p>
 *   排行榜服务接口
 * </p>
 * @author tianchunxu
 */
public interface RankingListService {

    /**
     * 获取热门视频排行榜
     *
     * @param query 查询参数
     * @return 排行榜结果
     */
    RestResult<List<RankingListVO>> getHotVideoRanking(RankingListQuery query);
}