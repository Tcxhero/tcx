package com.feisuanyz.common.dto;

import com.feisuanyz.common.constant.PublishTimeRequired;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   发布视频请求DTO
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPublishRequestDTO {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    @NotBlank(message = "视频标题不能为空")
    @Size(max = 50, message = "视频标题长度不能超过50字")
    private String title;

    @Size(max = 500, message = "视频简介长度不能超过500字")
    private String description;

    @NotNull(message = "分区ID不能为空")
    private Long categoryId;

    @Size(max = 5, message = "标签数量不能超过5个")
    private List<Long> tags;

    @NotNull(message = "可见性不能为空")
    private Integer visibility;

    @NotNull(message = "发布时间不能为空", groups = {PublishTimeRequired.class})
    private Date publishTime;
}