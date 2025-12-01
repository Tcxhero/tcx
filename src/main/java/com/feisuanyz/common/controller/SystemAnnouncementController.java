package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.AnnouncementEditDTO;
import com.feisuanyz.common.dto.AnnouncementOfflineDTO;
import com.feisuanyz.common.dto.AnnouncementPublishDTO;
import com.feisuanyz.common.dto.SystemAnnouncementDTO;
import com.feisuanyz.common.query.SystemAnnouncementQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.SystemAnnouncementService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 系统公告控制器
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/system/announcement")
public class SystemAnnouncementController {

    @Resource
    private SystemAnnouncementService systemAnnouncementService;

    /**
     * 发布系统公告
     * @param dto 发布参数
     * @param operatorId 操作员ID（从SecurityContext获取）
     * @return RestResult
     */
    @PostMapping("/publish")
    public RestResult<?> publish(@RequestBody @Valid AnnouncementPublishDTO dto,
                                 @RequestHeader(value = "operatorId", required = false) Long operatorId) {
        if (Objects.isNull(operatorId)) {
            operatorId = 1L; // 默认操作员ID
        }
        return systemAnnouncementService.publish(dto, operatorId);
    }

    /**
     * 编辑系统公告
     * @param dto 编辑参数
     * @param operatorId 操作员ID（从SecurityContext获取）
     * @return RestResult
     */
    @PostMapping("/edit")
    public RestResult<?> edit(@RequestBody @Valid AnnouncementEditDTO dto,
                              @RequestHeader(value = "operatorId", required = false) Long operatorId) {
        if (Objects.isNull(operatorId)) {
            operatorId = 1L; // 默认操作员ID
        }
        return systemAnnouncementService.edit(dto, operatorId);
    }

    /**
     * 下线系统公告
     * @param dto 下线参数
     * @param operatorId 操作员ID（从SecurityContext获取）
     * @return RestResult
     */
    @PostMapping("/offline")
    public RestResult<?> offline(@RequestBody @Valid AnnouncementOfflineDTO dto,
                                 @RequestHeader(value = "operatorId", required = false) Long operatorId) {
        if (Objects.isNull(operatorId)) {
            operatorId = 1L; // 默认操作员ID
        }
        return systemAnnouncementService.offline(dto, operatorId);
    }

    /**
     * 查询系统公告列表
     * @param query 查询参数
     * @return RestResult
     */
    @GetMapping("/list")
    public RestResult<Page<SystemAnnouncementDTO>> list(SystemAnnouncementQuery query) {
        return systemAnnouncementService.list(query);
    }

    /**
     * 查看公告详情
     * @param id 公告ID
     * @return RestResult
     */
    @GetMapping("/detail/{id}")
    public RestResult<SystemAnnouncementDTO> detail(@PathVariable Long id) {
        return systemAnnouncementService.detail(id);
    }
}