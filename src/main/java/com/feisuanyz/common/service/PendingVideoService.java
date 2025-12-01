package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.PendingVideoDTO;
import com.feisuanyz.common.dto.RestResult;
import com.feisuanyz.common.query.PendingVideoQuery;
import java.util.List;

/**
 * <p>
 *   待审核视频业务逻辑层接口
 * </p>
 * @author tianchunxu
 */
public interface PendingVideoService {
    RestResult<List<PendingVideoDTO>> getPendingVideoList(PendingVideoQuery query);
    RestResult<String> approveVideo(Long videoId);
    RestResult<String> rejectVideo(Long videoId, String reason);
    RestResult<String> takeDownVideo(Long videoId, String reason);
}