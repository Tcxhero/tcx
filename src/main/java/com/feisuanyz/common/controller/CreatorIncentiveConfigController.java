package com.feisuanyz.common.controller;

import com.feisuanyz.common.query.CreatorIncentiveConfigQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.CreatorIncentiveConfigService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 创作者激励预留配置Controller
 *
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/creator/incentive/config")
public class CreatorIncentiveConfigController {

    @Autowired
    private CreatorIncentiveConfigService configService;

    /**
     * 预留创作者激励配置
     *
     * @param query 配置查询对象
     * @return RestResult
     */
    @PostMapping("/reserve")
    public RestResult<?> reserveIncentive(@RequestBody @Valid CreatorIncentiveConfigQuery query) {
        log.info("预留创作者激励配置接口被调用，请求参数: {}", query);
        return configService.reserveIncentive(query);
    }

    /**
     * 查询创作者激励配置
     *
     * @param query 查询对象
     * @return RestResult
     */
    @GetMapping("/list")
    public RestResult<List<?>> getIncentiveConfigs(@ModelAttribute @Valid CreatorIncentiveConfigQuery query) {
        log.info("查询创作者激励配置接口被调用，请求参数: {}", query);
        return configService.getIncentiveConfigs(query);
    }

    /**
     * 更新创作者激励配置状态
     *
     * @param query 查询对象
     * @return RestResult
     */
    @PutMapping("/status")
    public RestResult<?> updateIncentiveStatus(@RequestBody @Valid CreatorIncentiveConfigQuery query) {
        log.info("更新创作者激励配置状态接口被调用，请求参数: {}", query);
        return configService.updateIncentiveStatus(query);
    }
}