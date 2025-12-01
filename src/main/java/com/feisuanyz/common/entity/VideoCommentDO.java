package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 *   视频评论实体对象
 * </p>
 * @author tianchunxu
 */
@Entity
@Table(name = "video_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoCommentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long videoId;

    private Long parentId;

    private Long replyToId;

    @NotNull
    private Long commenterId;

    @NotBlank
    private String content;

    private Integer likeCount;

    private Integer floorNumber;

    private Integer depthLevel;

    @NotNull
    private Integer status;

    private Long createBy;

    @CreationTimestamp
    private LocalDateTime createTime;

    private Long updateBy;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}