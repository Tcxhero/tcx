package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.VideoCategoryDTO;
import com.feisuanyz.common.response.RestResult;
import java.util.List;

/**
 * <p>
 *   视频分类业务逻辑接口
 * </p>
 * @author tianchunxu
 */
public interface VideoCategoryService {
    RestResult<List<VideoCategoryDTO>> getAllCategories();
}