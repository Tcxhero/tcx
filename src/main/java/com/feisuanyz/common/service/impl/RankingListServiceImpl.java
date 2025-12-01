package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.RankingListQuery;
import com.feisuanyz.common.dto.RankingListVO;
import com.feisuanyz.common.entity.RankingList;
import com.feisuanyz.common.repository.RankingListRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.RankingListService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *   排行榜服务实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class RankingListServiceImpl implements RankingListService {

    @Autowired
    private RankingListRepository rankingListRepository;

    @Override
    public RestResult<List<RankingListVO>> getHotVideoRanking(RankingListQuery query) {
        log.info("获取热门视频排行榜请求参数: {}", query);

        // 校验榜单类型参数是否合法
        if (query.getRankType() == null || (query.getRankType() != 1 && query.getRankType() != 2)) {
            return RestResult.fail("参数不合法", new ArrayList<>());
        }

        try {
            // 计算偏移量和限制条数
            int offset = (query.getPageNum() - 1) * query.getPageSize();
            int limit = query.getPageSize();

            // 查询指定类型的排行榜数据
            List<RankingList> rankingList = rankingListRepository.findByRankTypeOrderByScoreDesc(query.getRankType(), offset, limit);

            // 构造返回结果
            List<RankingListVO> result = new ArrayList<>();
            if (rankingList != null && !rankingList.isEmpty()) {
                for (RankingList item : rankingList) {
                    RankingListVO vo = new RankingListVO();
                    vo.setVideoId(item.getVideoId());
                    vo.setScore(item.getScore());
                    // 可以在这里补充更多字段，例如标题和封面图片URL等
                    result.add(vo);
                }
                return RestResult.success("调用成功", result);
            } else {
                return RestResult.success("暂无数据", new ArrayList<>());
            }
        } catch (Exception e) {
            log.error("获取热门视频排行榜异常", e);
            return RestResult.fail("系统异常", new ArrayList<>());
        }
    }
}