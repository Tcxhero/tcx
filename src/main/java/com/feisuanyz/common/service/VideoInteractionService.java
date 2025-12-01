package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.VideoInteractionDTO;
import com.feisuanyz.common.dto.VideoInteractionQuery;
import com.feisuanyz.common.response.RestResult;

/**
 * <p>
 *   视频互动行为业务逻辑层
 * </p>
 * @author tianchunxu
 */
public interface VideoInteractionService {

    RestResult likeVideo(VideoInteractionQuery query);

    RestResult unlikeVideo(VideoInteractionQuery query);

    RestResult coinVideo(VideoInteractionDTO dto);

    RestResult favoriteVideo(VideoInteractionQuery query);

    RestResult unfavoriteVideo(VideoInteractionQuery query);

    RestResult getInteractionStatus(VideoInteractionQuery query);
}