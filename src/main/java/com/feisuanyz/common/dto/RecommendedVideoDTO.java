package com.feisuanyz.common.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * <p>
 *   数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class RecommendedVideoDTO {

    private Long videoId;
    private String title;
    private String description;
    private String coverImageUrl;
    private Integer durationSeconds;
    private BigDecimal weightScore;
}