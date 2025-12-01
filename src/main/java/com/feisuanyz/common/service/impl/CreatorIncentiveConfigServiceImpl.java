package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.CreatorIncentiveConfigDTO;
import com.feisuanyz.common.entity.CreatorIncentiveConfig;
import com.feisuanyz.common.exception.BusinessException;
import com.feisuanyz.common.query.CreatorIncentiveConfigQuery;
import com.feisuanyz.common.repository.CreatorIncentiveConfigRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.CreatorIncentiveConfigService;
import com.feisuanyz.common.util.BeanUtil;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创作者激励预留配置Service实现类
 *
 * @author tianchunxu
 */
@Slf4j
@Service
@Transactional
public class CreatorIncentiveConfigServiceImpl implements CreatorIncentiveConfigService {

    @Autowired
    private CreatorIncentiveConfigRepository configRepository;

    @Override
    public RestResult<?> reserveIncentive(CreatorIncentiveConfigQuery query) {
        log.info("预留创作者激励配置，请求参数: {}", query);

        // 校验激励类型是否合法
        if (!isValidIncentiveType(query.getIncentiveType())) {
            return RestResult.error("激励类型不合法");
        }

        // 检查是否已存在相同类型的激励配置
        Optional<CreatorIncentiveConfig> existingConfig = configRepository.findByCreatorIdAndIncentiveType(
                query.getCreatorId(), query.getIncentiveType());
        if (existingConfig.isPresent()) {
            return RestResult.error("该激励类型已存在");
        }

        // 创建新的激励配置
        CreatorIncentiveConfig config = new CreatorIncentiveConfig();
        config.setCreatorId(query.getCreatorId());
        config.setIncentiveType(query.getIncentiveType());
        config.setStatus(query.getStatus());
        config.setCreateTime(LocalDateTime.now());
        config.setUpdateTime(LocalDateTime.now());

        try {
            configRepository.save(config);
            return RestResult.success();
        } catch (Exception e) {
            log.error("保存创作者激励配置失败", e);
            throw new BusinessException("保存创作者激励配置失败");
        }
    }

    @Override
    public RestResult<List<CreatorIncentiveConfigDTO>> getIncentiveConfigs(CreatorIncentiveConfigQuery query) {
        log.info("查询创作者激励配置，请求参数: {}", query);

        List<CreatorIncentiveConfig> configs = configRepository.findByCreatorId(query.getCreatorId());
        List<CreatorIncentiveConfigDTO> dtos = new ArrayList<>();
        for (CreatorIncentiveConfig config : configs) {
            dtos.add(BeanUtil.copyProperties(config, CreatorIncentiveConfigDTO.class));
        }

        return RestResult.success(dtos);
    }

    @Override
    public RestResult<?> updateIncentiveStatus(CreatorIncentiveConfigQuery query) {
        log.info("更新创作者激励配置状态，请求参数: {}", query);

        Optional<CreatorIncentiveConfig> optionalConfig = configRepository.findById(query.getConfigId());
        if (!optionalConfig.isPresent()) {
            return RestResult.error("配置信息不存在");
        }

        CreatorIncentiveConfig config = optionalConfig.get();
        config.setStatus(query.getStatus());
        config.setUpdateTime(LocalDateTime.now());

        try {
            configRepository.save(config);
            return RestResult.success();
        } catch (Exception e) {
            log.error("更新创作者激励配置状态失败", e);
            throw new BusinessException("更新创作者激励配置状态失败");
        }
    }

    /**
     * 校验激励类型是否合法
     *
     * @param incentiveType 激励类型
     * @return boolean
     */
    private boolean isValidIncentiveType(Integer incentiveType) {
        return incentiveType != null && (incentiveType == 1 || incentiveType == 2);
    }
}