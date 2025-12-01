package com.feisuanyz.common.dto;

import java.time.LocalDate;
import lombok.*;

/**
 * <p>
 *   创作者数据看板DTO类
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDashboardDTO {

    private Long id;
    private Long creatorId;
    private LocalDate statDate;
    private Integer viewCount;
    private Integer likeCount;
    private Integer coinCount;
    private Integer favoriteCount;
    private Integer fanGrowth;
    private String regionDistribution;
}