package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.SensitiveWordDTO;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.query.SensitiveWordQuery;
import org.springframework.data.domain.Page;

public interface SensitiveWordService {

    String filterSensitiveWords(String content);

    /**
     * 添加敏感词
     * @param dto 敏感词信息
     * @return RestResult
     */
    RestResult<?> addSensitiveWord(SensitiveWordDTO dto);

    /**
     * 删除敏感词
     * @param id 敏感词ID
     * @return RestResult
     */
    RestResult<?> deleteSensitiveWord(Long id);

    /**
     * 修改敏感词
     * @param dto 敏感词信息
     * @return RestResult
     */
    RestResult<?> updateSensitiveWord(SensitiveWordDTO dto);

    /**
     * 查询敏感词列表
     * @param query 查询条件
     * @return RestResult
     */
    RestResult<Page<SensitiveWordDTO>> listSensitiveWords(SensitiveWordQuery query);
}
