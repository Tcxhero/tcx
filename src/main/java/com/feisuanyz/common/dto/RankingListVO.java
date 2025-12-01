package com.feisuanyz.common.dto;

import java.net.URL;
import lombok.Data;

/**
 * <p>
 *   排行榜视图对象
 * </p>
 * @author tianchunxu
 */
@Data
public class RankingListVO {

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 综合得分
     */
    private Long score;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 封面图片URL
     */
    private String coverImageUrl;
}