package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.RankingListQuery;
import com.feisuanyz.common.dto.RankingListVO;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.RankingListService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   排行榜控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/ranking")
public class RankingListController {

    @Autowired
    private RankingListService rankingListService;

    /**
     * 获取热门视频排行榜
     *
     * @param query 查询参数
     * @return 排行榜结果
     */
    @GetMapping("/hot")
    public RestResult<List<RankingListVO>> getHotVideoRanking(@Valid RankingListQuery query) {
        log.info("获取热门视频排行榜接口被调用");
        return rankingListService.getHotVideoRanking(query);
    }
}