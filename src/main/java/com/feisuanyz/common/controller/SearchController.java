package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.SearchQuery;
import com.feisuanyz.common.dto.SearchResult;
import com.feisuanyz.common.entity.SearchHistory;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.SearchService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   搜索内容控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/content")
    public RestResult<SearchResult> searchContent(@Validated @RequestBody SearchQuery query) {
        try {
            SearchResult searchResult = searchService.searchContent(query);
            return new RestResult<>(RestResult.SUCCESS_CODE, "调用成功", searchResult);
        } catch (Exception e) {
            log.error("搜索内容时发生异常", e);
            return new RestResult<>(RestResult.FAILURE_CODE, "系统异常", null);
        }
    }

    @GetMapping("/history")
    public RestResult<List<SearchHistory>> getSearchHistory(@RequestParam Long userId,
                                                            @RequestParam(required = false, defaultValue = "10") int limit) {
        try {
            List<SearchHistory> searchHistory = searchService.getSearchHistory(userId, limit);
            return new RestResult<>(RestResult.SUCCESS_CODE, "调用成功", searchHistory);
        } catch (Exception e) {
            log.error("获取搜索历史时发生异常", e);
            return new RestResult<>(RestResult.FAILURE_CODE, "系统异常", null);
        }
    }

    @DeleteMapping("/history")
    public RestResult<Void> clearSearchHistory(@RequestParam Long userId) {
        try {
            searchService.clearSearchHistory(userId);
            return new RestResult<>(RestResult.SUCCESS_CODE, "调用成功", null);
        } catch (Exception e) {
            log.error("清空搜索历史时发生异常", e);
            return new RestResult<>(RestResult.FAILURE_CODE, "系统异常", null);
        }
    }
}