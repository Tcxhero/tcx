package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 *   评论点赞实体对象
 * </p>
 * @author tianchunxu
 */
@Entity
@Table(name = "comment_like")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long commentId;

    @NotNull
    private Long userId;

    private Long createBy;

    @CreationTimestamp
    private LocalDateTime createTime;

    private Long updateBy;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}