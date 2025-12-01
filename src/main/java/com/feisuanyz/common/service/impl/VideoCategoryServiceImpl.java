package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.VideoCategoryDTO;
import com.feisuanyz.common.entity.VideoCategoryEntity;
import com.feisuanyz.common.repository.VideoCategoryRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoCategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   视频分类业务逻辑实现
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class VideoCategoryServiceImpl implements VideoCategoryService {

    private final VideoCategoryRepository videoCategoryRepository;

    public VideoCategoryServiceImpl(VideoCategoryRepository videoCategoryRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public RestResult<List<VideoCategoryDTO>> getAllCategories() {
        List<VideoCategoryEntity> entities = videoCategoryRepository.findAllByOrderBySortOrderAsc();
        List<VideoCategoryDTO> dtos = entities.stream()
                .map(entity -> {
                    VideoCategoryDTO dto = new VideoCategoryDTO();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                })
                .collect(Collectors.toList());
        return RestResult.success(dtos);
    }
}