package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.SearchQuery;
import com.feisuanyz.common.dto.SearchResult;
import com.feisuanyz.common.entity.SearchHistory;
import java.util.List;

/**
 * <p>
 *   搜索业务逻辑接口
 * </p>
 * @author tianchunxu
 */
public interface SearchService {
    SearchResult searchContent(SearchQuery query);
    void clearSearchHistory(Long userId);
    List<SearchHistory> getSearchHistory(Long userId, int limit);
}