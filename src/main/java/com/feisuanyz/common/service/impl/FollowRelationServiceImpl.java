package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.FollowListRequestDTO;
import com.feisuanyz.common.dto.FollowRequestDTO;
import com.feisuanyz.common.entity.FollowRelationDO;
import com.feisuanyz.common.repository.FollowRelationRepository;
import com.feisuanyz.common.service.FollowRelationService;
import com.feisuanyz.common.service.UserProfileService;
import com.feisuanyz.common.util.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   用户关注关系表Service实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class FollowRelationServiceImpl implements FollowRelationService {
    @Autowired
    private FollowRelationRepository followRelationRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Override
    @Transactional
    public RestResult followUser(FollowRequestDTO requestDTO) {
        Long followerId = requestDTO.getFollowerId();
        Long followedId = requestDTO.getFollowedId();

        if (!userProfileService.checkUserExists(followerId) || !userProfileService.checkUserExists(followedId)) {
            return RestResult.fail("000001", "用户不存在");
        }

        if (followRelationRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            return RestResult.fail("000001", "已关注该用户");
        }

        FollowRelationDO followRelationDO = new FollowRelationDO();
        followRelationDO.setFollowerId(followerId);
        followRelationDO.setFollowedId(followedId);
        followRelationRepository.save(followRelationDO);

        return RestResult.success("调用成功");
    }

    @Override
    @Transactional
    public RestResult unfollowUser(FollowRequestDTO requestDTO) {
        Long followerId = requestDTO.getFollowerId();
        Long followedId = requestDTO.getFollowedId();

        if (!userProfileService.checkUserExists(followerId) || !userProfileService.checkUserExists(followedId)) {
            return RestResult.fail("000001", "用户不存在");
        }

        if (!followRelationRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            return RestResult.fail("000001", "未关注该用户");
        }

        followRelationRepository.deleteByFollowerIdAndFollowedId(followerId, followedId);

        return RestResult.success("调用成功");
    }

    @Override
    public RestResult getFollowList(FollowListRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();

        if (!userProfileService.checkUserExists(userId)) {
            return RestResult.fail("000001", "用户不存在");
        }

        Pageable pageable = PageRequest.of(requestDTO.getPageNum() - 1, requestDTO.getPageSize());
        Page<FollowRelationDO> followRelationDOPage = followRelationRepository.findByFollowerId(userId, pageable);

        return RestResult.success("调用成功", followRelationDOPage.getContent());
    }

    @Override
    public RestResult getFollowerList(FollowListRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();

        if (!userProfileService.checkUserExists(userId)) {
            return RestResult.fail("000001", "用户不存在");
        }

        Pageable pageable = PageRequest.of(requestDTO.getPageNum() - 1, requestDTO.getPageSize());
        Page<FollowRelationDO> followRelationDOPage = followRelationRepository.findByFollowedId(userId, pageable);

        return RestResult.success("调用成功", followRelationDOPage.getContent());
    }
}