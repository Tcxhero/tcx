package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 *   视频互动行为数据库实体对象
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "video_interaction")
@AllArgsConstructor
@Data
public class VideoInteractionDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long videoId;

    @NotNull
    private Long userId;

    @NotNull
    private Integer likeStatus;

    @NotNull
    private Integer coinAmount;

    @NotNull
    private Integer favoriteStatus;

    private Long createBy;

    @NotNull
    private LocalDateTime createTime;

    private Long updateBy;

    @NotNull
    private LocalDateTime updateTime;
}
