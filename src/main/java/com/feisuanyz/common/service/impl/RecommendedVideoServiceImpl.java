package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.RecommendedVideoDTO;
import com.feisuanyz.common.entity.RecommendedVideoDO;
import com.feisuanyz.common.entity.VideoInfoDO;
import com.feisuanyz.common.query.RecommendedVideoQuery;
import com.feisuanyz.common.repository.RecommendedVideoRepository;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.service.RecommendedVideoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   业务逻辑实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class RecommendedVideoServiceImpl implements RecommendedVideoService {

    @Autowired
    private RecommendedVideoRepository recommendedVideoRepository;

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Override
    public List<RecommendedVideoDTO> getRecommendedVideoList(RecommendedVideoQuery query) {
        try {
            List<RecommendedVideoDO> recommendations = recommendedVideoRepository.findByUserId(query.getUserId());
            if (!recommendations.isEmpty()) {
                return recommendations.stream()
                        .sorted((a, b) -> b.getWeightScore().compareTo(a.getWeightScore()))
                        .skip((query.getPageNum() - 1) * query.getPageSize())
                        .limit(query.getPageSize())
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                return getPopularVideos(query.getPageSize(), query.getPageNum());
            }
        } catch (Exception e) {
            log.error("获取个性化推荐视频列表时发生异常", e);
            throw new RuntimeException("系统异常", e);
        }
    }

    @Override
    @Transactional
    public void refreshUserRecommendation(Long userId) {
        try {
            // 查询用户的历史观看和互动行为数据
            // 计算兴趣匹配度并生成新的推荐视频列表
            List<RecommendedVideoDO> newRecommendations = calculateNewRecommendations(userId);

            // 删除旧的推荐记录
            recommendedVideoRepository.deleteByUserId(userId);

            // 插入新的推荐记录
            recommendedVideoRepository.saveAll(newRecommendations);
        } catch (Exception e) {
            log.error("刷新用户个性化推荐时发生异常", e);
            throw new RuntimeException("系统异常", e);
        }
    }

    @Override
    @Transactional
    public void clearUserRecommendation(Long userId) {
        try {
            recommendedVideoRepository.deleteByUserId(userId);
        } catch (Exception e) {
            log.error("清除用户个性化推荐记录时发生异常", e);
            throw new RuntimeException("系统异常", e);
        }
    }

    private RecommendedVideoDTO convertToDTO(RecommendedVideoDO recommendedVideoDO) {
        RecommendedVideoDTO dto = new RecommendedVideoDTO();
        dto.setVideoId(recommendedVideoDO.getVideoId());
        // 这里假设有一个方法可以从VideoInfoDO获取视频信息
        VideoInfoDO videoInfo = videoInfoRepository.findById(recommendedVideoDO.getVideoId()).orElse(null);
        if (videoInfo != null) {
            dto.setTitle(videoInfo.getTitle());
            dto.setDescription(videoInfo.getDescription());
            dto.setCoverImageUrl(videoInfo.getCoverImageUrl());
            dto.setDurationSeconds(videoInfo.getDurationSeconds());
        }
        dto.setWeightScore(recommendedVideoDO.getWeightScore());
        return dto;
    }

    private List<RecommendedVideoDO> calculateNewRecommendations(Long userId) {
        // 实现逻辑：根据用户行为数据计算兴趣匹配度并生成新的推荐视频列表
        // 这里仅作示例，实际需要根据业务逻辑实现
        return List.of(new RecommendedVideoDO(userId, 1L, 2, BigDecimal.valueOf(0.85), 1L, LocalDateTime.now(), 1L, LocalDateTime.now()));
    }

    private List<RecommendedVideoDTO> getPopularVideos(Integer pageSize, Integer pageNum) {
        // 实现逻辑：查询全站热门视频作为冷启动推荐
        // 这里仅作示例，实际需要根据业务逻辑实现
        Page<VideoInfoDO> popularVideos = videoInfoRepository.findAllByOrderByTranscodedStatusDesc(PageRequest.of(pageNum - 1, pageSize));
        return popularVideos.stream()
                .map(this::convertVideoInfoToDTO)
                .collect(Collectors.toList());
    }

    private RecommendedVideoDTO convertVideoInfoToDTO(VideoInfoDO videoInfoDO) {
        RecommendedVideoDTO dto = new RecommendedVideoDTO();
        dto.setVideoId(videoInfoDO.getId());
        dto.setTitle(videoInfoDO.getTitle());
        dto.setDescription(videoInfoDO.getDescription());
        dto.setCoverImageUrl(videoInfoDO.getCoverImageUrl());
        dto.setDurationSeconds(videoInfoDO.getDurationSeconds());
        // 这里假设冷启动推荐的权重分数为0.00
        dto.setWeightScore(BigDecimal.ZERO);
        return dto;
    }
}