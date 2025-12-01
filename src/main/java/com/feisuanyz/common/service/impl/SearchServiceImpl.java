package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.SearchQuery;
import com.feisuanyz.common.dto.SearchResult;
import com.feisuanyz.common.dto.UserProfileDTO;
import com.feisuanyz.common.dto.VideoInfoDTO;
import com.feisuanyz.common.dto.VideoTagDTO;
import com.feisuanyz.common.entity.SearchHistory;
import com.feisuanyz.common.entity.UserProfile;
import com.feisuanyz.common.entity.VideoInfo;
import com.feisuanyz.common.entity.VideoTag;
import com.feisuanyz.common.repository.SearchHistoryRepository;
import com.feisuanyz.common.repository.UserProfileRepository;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.repository.VideoTagMappingRepository;
import com.feisuanyz.common.repository.VideoTagRepository;
import com.feisuanyz.common.service.SearchService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   搜索业务逻辑实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private VideoTagRepository videoTagRepository;

    @Autowired
    private VideoTagMappingRepository videoTagMappingRepository;

    @Override
    @Transactional
    public SearchResult searchContent(SearchQuery query) {
        try {
            // 记录搜索历史
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setUserId(query.getUserId());
            searchHistory.setKeyword(query.getKeyword());
            searchHistory.setSearchTime(new Date());
            searchHistoryRepository.save(searchHistory);

            // 构建分页对象
            Pageable pageable = PageRequest.of(query.getPageNo() - 1, query.getPageSize());

            // 搜索视频
            Page<VideoInfo> videoResults = videoInfoRepository.searchVideos(query.getKeyword(), query.getPublishTimeStart(),
                    query.getPublishTimeEnd(), query.getMinDuration(), query.getMaxDuration(), query.getCategoryId(), pageable);

            // 搜索UP主
            List<UserProfile> userResults = userProfileRepository.searchUsers(query.getKeyword());

            // 搜索标签
            List<VideoTag> tagResults = videoTagRepository.searchTags(query.getKeyword());

            // 转换为DTO
            List<VideoInfoDTO> videoDTOs = convertToVideoDTOs(videoResults.getContent());
            List<UserProfileDTO> userDTOs = convertToUserProfileDTOs(userResults);
            List<VideoTagDTO> tagDTOs = convertToVideoTagDTOs(tagResults);

            // 构建搜索结果
            SearchResult searchResult = new SearchResult();
            searchResult.setVideoResults(videoDTOs);
            searchResult.setUserResults(userDTOs);
            searchResult.setTagResults(tagDTOs);

            return searchResult;
        } catch (Exception e) {
            log.error("搜索内容时发生异常", e);
            throw new RuntimeException("系统异常");
        }
    }

    private List<VideoInfoDTO> convertToVideoDTOs(List<VideoInfo> videoInfos) {
        List<VideoInfoDTO> videoDTOs = new ArrayList<>();
        for (VideoInfo videoInfo : videoInfos) {
            VideoInfoDTO videoDTO = new VideoInfoDTO();
            BeanUtils.copyProperties(videoInfo, videoDTO);
            videoDTOs.add(videoDTO);
        }
        return videoDTOs;
    }

    private List<UserProfileDTO> convertToUserProfileDTOs(List<UserProfile> userProfiles) {
        List<UserProfileDTO> userDTOs = new ArrayList<>();
        for (UserProfile userProfile : userProfiles) {
            UserProfileDTO userDTO = new UserProfileDTO();
            BeanUtils.copyProperties(userProfile, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    private List<VideoTagDTO> convertToVideoTagDTOs(List<VideoTag> videoTags) {
        List<VideoTagDTO> tagDTOs = new ArrayList<>();
        for (VideoTag videoTag : videoTags) {
            VideoTagDTO tagDTO = new VideoTagDTO();
            BeanUtils.copyProperties(videoTag, tagDTO);
            tagDTOs.add(tagDTO);
        }
        return tagDTOs;
    }

    @Override
    @Transactional
    public void clearSearchHistory(Long userId) {
        try {
            searchHistoryRepository.deleteByUserId(userId);
        } catch (Exception e) {
            log.error("清空搜索历史时发生异常", e);
            throw new RuntimeException("系统异常");
        }
    }

    @Override
    public List<SearchHistory> getSearchHistory(Long userId, int limit) {
        try {
            return searchHistoryRepository.findByUserIdOrderBySearchTimeDesc(userId).stream().limit(limit).toList();
        } catch (Exception e) {
            log.error("获取搜索历史时发生异常", e);
            throw new RuntimeException("系统异常");
        }
    }
}