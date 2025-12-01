package com.feisuanyz.controller;

import com.feisuanyz.common.dto.SensitiveWordDTO;
import com.feisuanyz.common.query.SensitiveWordQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.SensitiveWordService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   敏感词管理控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/sensitive-word")
public class SensitiveWordController {

    @Autowired
    private SensitiveWordService sensitiveWordService;

    /**
     * 添加敏感词
     * @param dto 敏感词信息
     * @return RestResult
     */
    @PostMapping("/add")
    public RestResult<?> addSensitiveWord(@Valid @RequestBody SensitiveWordDTO dto) {
        log.info("添加敏感词请求: {}", dto);
        return sensitiveWordService.addSensitiveWord(dto);
    }

    /**
     * 删除敏感词
     * @param id 敏感词ID
     * @return RestResult
     */
    @DeleteMapping("/delete/{id}")
    public RestResult<?> deleteSensitiveWord(@PathVariable Long id) {
        log.info("删除敏感词请求 ID: {}", id);
        return sensitiveWordService.deleteSensitiveWord(id);
    }

    /**
     * 修改敏感词
     * @param dto 敏感词信息
     * @return RestResult
     */
    @PutMapping("/update")
    public RestResult<?> updateSensitiveWord(@Valid @RequestBody SensitiveWordDTO dto) {
        log.info("修改敏感词请求: {}", dto);
        return sensitiveWordService.updateSensitiveWord(dto);
    }

    /**
     * 查询敏感词列表
     * @param query 查询条件
     * @return RestResult
     */
    @GetMapping("/list")
    public RestResult<?> listSensitiveWords(SensitiveWordQuery query) {
        log.info("查询敏感词列表请求: {}", query);
        return sensitiveWordService.listSensitiveWords(query);
    }
}