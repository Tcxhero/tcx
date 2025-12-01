package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.CreatorDashboardDTO;
import com.feisuanyz.common.query.CreatorDashboardQuery;
import java.util.List;

/**
 * <p>
 *   创作者数据看板Service接口
 * </p>
 * @author tianchunxu
 */
public interface CreatorDashboardService {

    List<CreatorDashboardDTO> getViewTrendData(CreatorDashboardQuery query);
    List<CreatorDashboardDTO> getLikeChangeData(CreatorDashboardQuery query);
    Integer getFanGrowth(CreatorDashboardQuery query);
    String getRegionDistribution(CreatorDashboardQuery query);
}