package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.ResponseCode;
import com.feisuanyz.common.dto.VideoPublishRequestDTO;
import com.feisuanyz.common.dto.VideoPublishResponseDTO;
import com.feisuanyz.common.dto.VideoQuery;
import com.feisuanyz.common.entity.VideoInfoDO;
import com.feisuanyz.common.entity.VideoTagDO;
import com.feisuanyz.common.entity.VideoTagMappingDO;
import com.feisuanyz.common.exception.BusinessException;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.repository.VideoTagMappingRepository;
import com.feisuanyz.common.repository.VideoTagRepository;
import com.feisuanyz.common.service.VideoService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import com.feisuanyz.common.query.VisibilityQuery;
import com.feisuanyz.common.dto.VideoInfoDTO;
import com.feisuanyz.common.query.VideoQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.query.EditVideoQuery;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 *   视频服务实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Autowired
    private VideoTagMappingRepository videoTagMappingRepository;

    @Autowired
    private VideoTagRepository videoTagRepository;

    @Override
    @Transactional
    public VideoPublishResponseDTO publishVideo(VideoPublishRequestDTO requestDTO) {
        validateRequest(requestDTO);
        Optional<VideoInfoDO> videoInfoOptional = videoInfoRepository.findById(requestDTO.getVideoId());
        if (videoInfoOptional.isEmpty()) {
            throw new BusinessException(ResponseCode.VIDEO_NOT_FOUND, "视频未找到");
        }
        VideoInfoDO videoInfoDO = videoInfoOptional.get();
        videoInfoDO.setTitle(requestDTO.getTitle());
        videoInfoDO.setDescription(requestDTO.getDescription());
        videoInfoDO.setCategoryId(requestDTO.getCategoryId());
        videoInfoDO.setVisibility(requestDTO.getVisibility());
        videoInfoDO.setPublishTime(requestDTO.getPublishTime());
        videoInfoDO.setUpdateTime(new Date());
        videoInfoRepository.save(videoInfoDO);
        videoTagMappingRepository.deleteByVideoId(requestDTO.getVideoId());
        if (requestDTO.getTags() != null && !requestDTO.getTags().isEmpty()) {
            List<VideoTagMappingDO> mappings = requestDTO.getTags().stream().map(tagId -> new VideoTagMappingDO(null, requestDTO.getVideoId(), tagId, null, new Date(), null, new Date())).collect(Collectors.toList());
            videoTagMappingRepository.saveAll(mappings);
        }
        return buildResponse(videoInfoDO);
    }

    @Override
    public VideoPublishResponseDTO getVideoPublishDetails(VideoQuery query) {
        Optional<VideoInfoDO> videoInfoOptional = videoInfoRepository.findById(query.getVideoId());
        if (videoInfoOptional.isEmpty()) {
            throw new BusinessException(ResponseCode.VIDEO_NOT_FOUND, "视频未找到");
        }
        VideoInfoDO videoInfoDO = videoInfoOptional.get();
        List<VideoTagMappingDO> mappings = videoTagMappingRepository.findByVideoId(videoInfoDO.getId());
        List<String> tagNames = mappings.stream().map(mapping -> videoTagRepository.findById(mapping.getTagId()).orElse(new VideoTagDO()).getTagName()).collect(Collectors.toList());
        VideoPublishResponseDTO responseDTO = buildResponse(videoInfoDO);
        responseDTO.setTags(tagNames);
        return responseDTO;
    }

    private void validateRequest(VideoPublishRequestDTO requestDTO) {
        if (requestDTO.getTitle().length() > 50) {
            throw new BusinessException(ResponseCode.TITLE_TOO_LONG, "视频标题过长");
        }
        if (requestDTO.getDescription() != null && requestDTO.getDescription().length() > 500) {
            throw new BusinessException(ResponseCode.DESCRIPTION_TOO_LONG, "视频简介过长");
        }
        if (requestDTO.getTags() != null && requestDTO.getTags().size() > 5) {
            throw new BusinessException(ResponseCode.TAGS_LIMIT_EXCEEDED, "标签数量超出限制");
        }
        if (!List.of(1, 2, 3).contains(requestDTO.getVisibility())) {
            throw new BusinessException(ResponseCode.INVALID_VISIBILITY, "可见性设置不合法");
        }
        if (requestDTO.getVisibility() == 3 && requestDTO.getPublishTime() == null) {
            throw new BusinessException(ResponseCode.PUBLISH_TIME_REQUIRED, "定时发布必须指定发布时间");
        }
    }

    private VideoPublishResponseDTO buildResponse(VideoInfoDO videoInfoDO) {
        return new VideoPublishResponseDTO(videoInfoDO.getId(), videoInfoDO.getTitle(), videoInfoDO.getDescription(), videoInfoDO.getCategoryId(), null, videoInfoDO.getVisibility(), videoInfoDO.getPublishTime());
    }

    @Override
    public RestResult<List<VideoInfoDTO>> getPublishedVideoList(VideoQuery query) {
        List<VideoInfoDO> videoInfoDOList = videoInfoRepository.findByUploaderIdAndVisibility(query.getUploaderId(), 1, PageRequest.of(query.getPage() - 1, query.getSize()));
        if (videoInfoDOList.isEmpty()) {
            return RestResult.success("暂无视频内容", List.of());
        }
        List<VideoInfoDTO> videoInfoDTOList = videoInfoDOList.stream().map(this::convertToDTO).collect(Collectors.toList());
        return RestResult.success("调用成功", videoInfoDTOList);
    }

    @Override
    public RestResult<VideoInfoDTO> getVideoDetail(Long videoId) {
        Optional<VideoInfoDO> videoInfoDOOptional = Optional.ofNullable(videoInfoRepository.findByIdAndVisibility(videoId, 1));
        if (videoInfoDOOptional.isEmpty()) {
            return RestResult.failure("000001", "视频不存在");
        }
        VideoInfoDTO videoInfoDTO = convertToDTO(videoInfoDOOptional.get());
        return RestResult.success("调用成功", videoInfoDTO);
    }

    @Override
    @Transactional
    public RestResult<Void> editVideoInfo(EditVideoQuery query) {
        VideoInfoDO videoInfoDO = videoInfoRepository.findByIdAndVisibility(query.getVideoId(), 1);
        if (videoInfoDO == null) {
            return RestResult.failure("000001", "编辑失败，视频不存在或无权限");
        }
        if (query.getTitle() != null) {
            videoInfoDO.setTitle(query.getTitle());
        }
        if (query.getDescription() != null) {
            videoInfoDO.setDescription(query.getDescription());
        }
        if (query.getCategoryId() != null) {
            videoInfoDO.setCategoryId(query.getCategoryId());
        }
        if (query.getVisibility() != null) {
            videoInfoDO.setVisibility(query.getVisibility());
        }
        videoInfoDO.setUpdateTime(new Date());
        videoInfoRepository.save(videoInfoDO);
        if (query.getTagIds() != null) {
            videoTagMappingRepository.deleteByVideoId(query.getVideoId());
            List<VideoTagMappingDO> tagMappings = query.getTagIds().stream().map(tagId -> new VideoTagMappingDO(null, query.getVideoId(), tagId, null, new Date(), null, new Date())).collect(Collectors.toList());
            videoTagMappingRepository.saveAll(tagMappings);
        }
        return RestResult.success("编辑成功");
    }

    @Override
    @Transactional
    public RestResult<Void> deleteVideo(Long videoId) {
        VideoInfoDO videoInfoDO = videoInfoRepository.findByIdAndVisibility(videoId, 1);
        if (videoInfoDO == null) {
            return RestResult.failure("000001", "删除失败，视频不存在或无权限");
        }
        // 逻辑删除
        videoInfoDO.setVisibility(0);
        videoInfoDO.setUpdateTime(new Date());
        videoInfoRepository.save(videoInfoDO);
        return RestResult.success("删除成功");
    }

    @Override
    @Transactional
    public RestResult<Void> setVideoVisibility(VisibilityQuery query) {
        VideoInfoDO videoInfoDO = videoInfoRepository.findByIdAndVisibility(query.getVideoId(), 1);
        if (videoInfoDO == null) {
            return RestResult.failure("000001", "设置失败，视频不存在或无权限");
        }
        videoInfoDO.setVisibility(query.getVisibility());
        videoInfoDO.setUpdateTime(new Date());
        videoInfoRepository.save(videoInfoDO);
        return RestResult.success("可见性设置成功");
    }

    private VideoInfoDTO convertToDTO(VideoInfoDO videoInfoDO) {
        VideoInfoDTO videoInfoDTO = new VideoInfoDTO();
        BeanUtils.copyProperties(videoInfoDO, videoInfoDTO);
        List<String> tagNames = videoInfoDO.getVideoTagMappings().stream().map(mapping -> mapping.getTag().getTagName()).collect(Collectors.toList());
        videoInfoDTO.setTagNames(tagNames);
        return videoInfoDTO;
    }
}
