package com.feisuanyz.common.dto;

import com.feisuanyz.common.dto.UserProfileDTO;
import com.feisuanyz.common.dto.VideoInfoDTO;
import com.feisuanyz.common.dto.VideoTagDTO;
import java.util.List;
import lombok.Data;

/**
 * <p>
 *   搜索结果封装对象
 * </p>
 * @author tianchunxu
 */
@Data
public class SearchResult {
    private List<VideoInfoDTO> videoResults;
    private List<UserProfileDTO> userResults;
    private List<VideoTagDTO> tagResults;
}