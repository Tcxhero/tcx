package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.DynamicFeedDTO;
import com.feisuanyz.common.dto.DynamicFeedQuery;
import java.util.List;

/**
 * <p>
 *   动态信息流表业务逻辑层
 * </p>
 * @author tianchunxu
 */
public interface DynamicFeedService {

    List<DynamicFeedDTO> getDynamicFeedList(DynamicFeedQuery query);
}