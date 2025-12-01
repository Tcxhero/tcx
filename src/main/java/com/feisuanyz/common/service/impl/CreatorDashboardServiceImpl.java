package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.CreatorDashboardDTO;
import com.feisuanyz.common.entity.CreatorDashboardData;
import com.feisuanyz.common.query.CreatorDashboardQuery;
import com.feisuanyz.common.repository.CreatorDashboardRepository;
import com.feisuanyz.common.service.CreatorDashboardService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   创作者数据看板Service实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class CreatorDashboardServiceImpl implements CreatorDashboardService {

    private final CreatorDashboardRepository creatorDashboardRepository;

    public CreatorDashboardServiceImpl(CreatorDashboardRepository creatorDashboardRepository) {
        this.creatorDashboardRepository = creatorDashboardRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreatorDashboardDTO> getViewTrendData(CreatorDashboardQuery query) {
        LocalDate startDate = query.getStatDate().minusDays(query.getDays());
        LocalDate endDate = query.getStatDate();
        List<CreatorDashboardData> dataList = creatorDashboardRepository.findByCreatorIdAndStatDateBetween(query.getCreatorId(), startDate, endDate);
        return dataList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreatorDashboardDTO> getLikeChangeData(CreatorDashboardQuery query) {
        LocalDate startDate = query.getStatDate().minusDays(query.getDays());
        LocalDate endDate = query.getStatDate();
        List<CreatorDashboardData> dataList = creatorDashboardRepository.findByCreatorIdAndStatDateBetween(query.getCreatorId(), startDate, endDate);
        return dataList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getFanGrowth(CreatorDashboardQuery query) {
        LocalDate startDate = query.getStatDate().minusDays(query.getDays());
        LocalDate endDate = query.getStatDate();
        List<CreatorDashboardData> dataList = creatorDashboardRepository.findByCreatorIdAndStatDateBetween(query.getCreatorId(), startDate, endDate);
        return dataList.stream().mapToInt(CreatorDashboardData::getFanGrowth).sum();
    }

    @Override
    @Transactional(readOnly = true)
    public String getRegionDistribution(CreatorDashboardQuery query) {
        LocalDate startDate = query.getStatDate().minusDays(query.getDays());
        LocalDate endDate = query.getStatDate();
        List<CreatorDashboardData> dataList = creatorDashboardRepository.findByCreatorIdAndStatDateBetween(query.getCreatorId(), startDate, endDate);
        return dataList.stream().map(CreatorDashboardData::getRegionDistribution).collect(Collectors.joining(", "));
    }

    private CreatorDashboardDTO convertToDTO(CreatorDashboardData data) {
        CreatorDashboardDTO dto = new CreatorDashboardDTO();
        BeanUtils.copyProperties(data, dto);
        return dto;
    }
}