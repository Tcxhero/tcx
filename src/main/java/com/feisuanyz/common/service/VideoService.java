package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.VideoPublishRequestDTO;
import com.feisuanyz.common.dto.VideoPublishResponseDTO;
import com.feisuanyz.common.dto.VideoQuery;
import java.util.List;
import com.feisuanyz.common.query.VideoQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.query.EditVideoQuery;
import com.feisuanyz.common.query.VisibilityQuery;
import com.feisuanyz.common.dto.VideoInfoDTO;

/**
 * <p>
 *   视频服务接口
 * </p>
 * @author tianchunxu
 */
public interface VideoService {

    VideoPublishResponseDTO publishVideo(VideoPublishRequestDTO requestDTO);

    VideoPublishResponseDTO getVideoPublishDetails(VideoQuery query);

    RestResult<List<VideoInfoDTO>> getPublishedVideoList(VideoQuery query);

    RestResult<VideoInfoDTO> getVideoDetail(Long videoId);

    RestResult<Void> editVideoInfo(EditVideoQuery query);

    RestResult<Void> deleteVideo(Long videoId);

    RestResult<Void> setVideoVisibility(VisibilityQuery query);
}
