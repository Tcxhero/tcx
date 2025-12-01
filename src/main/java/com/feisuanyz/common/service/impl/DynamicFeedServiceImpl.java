package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.DynamicFeedDTO;
import com.feisuanyz.common.dto.DynamicFeedQuery;
import com.feisuanyz.common.entity.DynamicFeed;
import com.feisuanyz.common.repository.DynamicFeedRepository;
import com.feisuanyz.common.repository.FollowRelationRepository;
import com.feisuanyz.common.service.DynamicFeedService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   动态信息流表业务逻辑实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class DynamicFeedServiceImpl implements DynamicFeedService {

    @Autowired
    private FollowRelationRepository followRelationRepository;

    @Autowired
    private DynamicFeedRepository dynamicFeedRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DynamicFeedDTO> getDynamicFeedList(DynamicFeedQuery query) {
        List<Long> followedIds = followRelationRepository.findFollowedIdsByFollowerId(query.getUserId());
        if (followedIds.isEmpty()) {
            return List.of();
        }

        Pageable pageable = PageRequest.of(0, query.getPageSize() != null ? query.getPageSize() : 10);
        List<DynamicFeed> dynamicFeeds = dynamicFeedRepository.findByUserIdInOrderByCreateTimeDesc(followedIds, pageable);

        return dynamicFeeds.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DynamicFeedDTO convertToDTO(DynamicFeed dynamicFeed) {
        DynamicFeedDTO dto = new DynamicFeedDTO();
        BeanUtils.copyProperties(dynamicFeed, dto);
        dto.setCreateTime(dynamicFeed.getCreateTime().toString());
        return dto;
    }
}