package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.StatusConstants;
import com.feisuanyz.common.entity.SensitiveWordDO;
import com.feisuanyz.common.repository.SensitiveWordRepository;
import com.feisuanyz.common.service.SensitiveWordService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.data.domain.PageRequest;
import com.feisuanyz.common.query.SensitiveWordQuery;
import org.springframework.data.domain.Page;
import java.util.stream.Collectors;
import com.feisuanyz.common.dto.SensitiveWordDTO;
import com.feisuanyz.common.entity.SensitiveWord;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import com.feisuanyz.common.response.RestResult;
import org.springframework.data.domain.PageImpl;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 *   敏感词过滤业务逻辑实现
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
@Transactional
public class SensitiveWordServiceImpl implements SensitiveWordService {

    @Autowired
    private SensitiveWordRepository sensitiveWordRepository;

    @Override
    public String filterSensitiveWords(String content) {
        List<SensitiveWordDO> sensitiveWords = sensitiveWordRepository.findByStatus(StatusConstants.SENSITIVE_WORD_STATUS_ENABLED);
        for (SensitiveWordDO word : sensitiveWords) {
            if (content.contains(word.getWordContent())) {
                content = content.replaceAll(word.getWordContent(), word.getWordContent().replaceAll(".", "*"));
            }
        }
        return content;
    }

    /**
     * 添加敏感词
     * @param dto 敏感词信息
     * @return RestResult
     */
    @Override
    public RestResult<?> addSensitiveWord(SensitiveWordDTO dto) {
        // 校验敏感词内容是否为空
        if (dto.getWordContent() == null || dto.getWordContent().trim().isEmpty()) {
            return RestResult.error("敏感词内容不能为空");
        }
        // 检查敏感词是否已存在
        Optional<SensitiveWord> existing = sensitiveWordRepository.findByWordContentAndWordType(dto.getWordContent(), dto.getWordType());
        if (existing.isPresent()) {
            return RestResult.error("敏感词内容已存在");
        }
        // 构建实体对象
        SensitiveWord entity = new SensitiveWord();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        // 保存到数据库
        sensitiveWordRepository.save(entity);
        return RestResult.success("添加成功");
    }

    /**
     * 删除敏感词
     * @param id 敏感词ID
     * @return RestResult
     */
    @Override
    public RestResult<?> deleteSensitiveWord(Long id) {
        // 检查敏感词是否存在
        Optional<SensitiveWord> optional = sensitiveWordRepository.findById(id);
        if (!optional.isPresent()) {
            return RestResult.error("敏感词不存在");
        }
        // 执行删除操作
        sensitiveWordRepository.deleteById(id);
        return RestResult.success("删除成功");
    }

    /**
     * 修改敏感词
     * @param dto 敏感词信息
     * @return RestResult
     */
    @Override
    public RestResult<?> updateSensitiveWord(SensitiveWordDTO dto) {
        // 检查敏感词是否存在
        Optional<SensitiveWord> optional = sensitiveWordRepository.findById(dto.getId());
        if (!optional.isPresent()) {
            return RestResult.error("敏感词不存在");
        }
        SensitiveWord entity = optional.get();
        // 如果提供了新的内容，则更新
        if (dto.getWordContent() != null && !dto.getWordContent().trim().isEmpty()) {
            // 检查是否有其他重复的敏感词
            Optional<SensitiveWord> duplicate = sensitiveWordRepository.findByWordContentAndWordType(dto.getWordContent(), dto.getWordType());
            if (duplicate.isPresent() && !duplicate.get().getId().equals(dto.getId())) {
                return RestResult.error("敏感词内容已存在");
            }
            entity.setWordContent(dto.getWordContent());
        }
        // 更新其他字段
        if (dto.getWordType() != null) {
            entity.setWordType(dto.getWordType());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        entity.setUpdateTime(LocalDateTime.now());
        // 保存更改
        sensitiveWordRepository.save(entity);
        return RestResult.success("修改成功");
    }

    /**
     * 查询敏感词列表
     * @param query 查询条件
     * @return RestResult
     */
    @Override
    public RestResult<Page<SensitiveWordDTO>> listSensitiveWords(SensitiveWordQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNo() - 1, query.getPageSize());
        // 这里可以添加更复杂的查询逻辑（比如按条件过滤等），但目前只做基本分页查询
        Page<SensitiveWord> page = sensitiveWordRepository.findAll(pageable);
        List<SensitiveWordDTO> dtos = page.getContent().stream().map(item -> {
            SensitiveWordDTO dto = new SensitiveWordDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
        Page<SensitiveWordDTO> resultPage = new PageImpl<>(dtos, pageable, page.getTotalElements());
        return RestResult.success(resultPage);
    }
}
