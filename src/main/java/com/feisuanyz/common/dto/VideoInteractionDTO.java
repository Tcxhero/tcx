package com.feisuanyz.common.dto;

import lombok.Data;

/**
 * <p>
 *   视频互动行为数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class VideoInteractionDTO {

    private Long userId;
    private Long videoId;
    private Integer likeStatus;
    private Integer coinAmount;
    private Integer favoriteStatus;
}