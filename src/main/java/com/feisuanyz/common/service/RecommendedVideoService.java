package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.RecommendedVideoDTO;
import com.feisuanyz.common.query.RecommendedVideoQuery;
import java.util.List;

/**
 * <p>
 *   业务逻辑接口
 * </p>
 * @author tianchunxu
 */
public interface RecommendedVideoService {

    List<RecommendedVideoDTO> getRecommendedVideoList(RecommendedVideoQuery query);

    void refreshUserRecommendation(Long userId);

    void clearUserRecommendation(Long userId);
}