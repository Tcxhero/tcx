package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * <p>
 *   数据库实体对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recommended_video")
public class RecommendedVideoDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long videoId;

    @NotNull
    private Integer reasonType;

    @NotNull
    private BigDecimal weightScore;

    private Long createBy;

    @NotNull
    private LocalDateTime createTime;

    private Long updateBy;

    @NotNull
    private LocalDateTime updateTime;
}