package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.AnnouncementEditDTO;
import com.feisuanyz.common.dto.AnnouncementOfflineDTO;
import com.feisuanyz.common.dto.AnnouncementPublishDTO;
import com.feisuanyz.common.dto.SystemAnnouncementDTO;
import com.feisuanyz.common.query.SystemAnnouncementQuery;
import com.feisuanyz.common.response.RestResult;
import org.springframework.data.domain.Page;

/**
 * 系统公告服务接口
 * @author tianchunxu
 */
public interface SystemAnnouncementService {

    /**
     * 发布系统公告
     * @param dto 发布参数
     * @param operatorId 操作员ID
     * @return RestResult
     */
    RestResult<?> publish(AnnouncementPublishDTO dto, Long operatorId);

    /**
     * 编辑系统公告
     * @param dto 编辑参数
     * @param operatorId 操作员ID
     * @return RestResult
     */
    RestResult<?> edit(AnnouncementEditDTO dto, Long operatorId);

    /**
     * 下线系统公告
     * @param dto 下线参数
     * @param operatorId 操作员ID
     * @return RestResult
     */
    RestResult<?> offline(AnnouncementOfflineDTO dto, Long operatorId);

    /**
     * 查询系统公告列表
     * @param query 查询参数
     * @return RestResult
     */
    RestResult<Page<SystemAnnouncementDTO>> list(SystemAnnouncementQuery query);

    /**
     * 查看公告详情
     * @param id 公告ID
     * @return RestResult
     */
    RestResult<SystemAnnouncementDTO> detail(Long id);
}