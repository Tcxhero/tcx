package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.CreatorDashboardDTO;
import com.feisuanyz.common.query.CreatorDashboardQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.CreatorDashboardService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   创作者数据看板Controller类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/creator-dashboard")
public class CreatorDashboardController {

    private final CreatorDashboardService creatorDashboardService;

    @Autowired
    public CreatorDashboardController(CreatorDashboardService creatorDashboardService) {
        this.creatorDashboardService = creatorDashboardService;
    }

    @GetMapping("/view-trend")
    public RestResult<List<CreatorDashboardDTO>> getViewTrendData(@Validated CreatorDashboardQuery query) {
        List<CreatorDashboardDTO> dataList = creatorDashboardService.getViewTrendData(query);
        return RestResult.success(dataList);
    }

    @GetMapping("/like-change")
    public RestResult<List<CreatorDashboardDTO>> getLikeChangeData(@Validated CreatorDashboardQuery query) {
        List<CreatorDashboardDTO> dataList = creatorDashboardService.getLikeChangeData(query);
        return RestResult.success(dataList);
    }

    @GetMapping("/fan-growth")
    public RestResult<Integer> getFanGrowth(@Validated CreatorDashboardQuery query) {
        Integer fanGrowth = creatorDashboardService.getFanGrowth(query);
        return RestResult.success(fanGrowth);
    }

    @GetMapping("/region-distribution")
    public RestResult<String> getRegionDistribution(@Validated CreatorDashboardQuery query) {
        String regionDistribution = creatorDashboardService.getRegionDistribution(query);
        return RestResult.success(regionDistribution);
    }
}