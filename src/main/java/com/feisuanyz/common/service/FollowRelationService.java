package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.FollowListRequestDTO;
import com.feisuanyz.common.dto.FollowRequestDTO;
import com.feisuanyz.common.util.RestResult;

/**
 * <p>
 *   用户关注关系表Service接口
 * </p>
 * @author tianchunxu
 */
public interface FollowRelationService {
    RestResult followUser(FollowRequestDTO requestDTO);

    RestResult unfollowUser(FollowRequestDTO requestDTO);

    RestResult getFollowList(FollowListRequestDTO requestDTO);

    RestResult getFollowerList(FollowListRequestDTO requestDTO);
}