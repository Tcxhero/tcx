package com.feisuanyz.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   视频与标签关联实体类
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@Entity
@Table(name = "video_tag_mapping")
@AllArgsConstructor
@Data
public class VideoTagMappingDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_id", nullable = false)
    private Long videoId;

    @Column(name = "tag_id", nullable = false)
    private Long tagId;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
