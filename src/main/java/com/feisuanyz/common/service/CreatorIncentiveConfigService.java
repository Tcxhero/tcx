package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.CreatorIncentiveConfigDTO;
import com.feisuanyz.common.query.CreatorIncentiveConfigQuery;
import com.feisuanyz.common.response.RestResult;
import java.util.List;

/**
 * 创作者激励预留配置Service接口
 *
 * @author tianchunxu
 */
public interface CreatorIncentiveConfigService {

    /**
     * 预留创作者激励配置
     *
     * @param query 配置查询对象
     * @return RestResult
     */
    RestResult<?> reserveIncentive(CreatorIncentiveConfigQuery query);

    /**
     * 查询创作者激励配置
     *
     * @param query 查询对象
     * @return RestResult
     */
    RestResult<List<CreatorIncentiveConfigDTO>> getIncentiveConfigs(CreatorIncentiveConfigQuery query);

    /**
     * 更新创作者激励配置状态
     *
     * @param query 查询对象
     * @return RestResult
     */
    RestResult<?> updateIncentiveStatus(CreatorIncentiveConfigQuery query);
}