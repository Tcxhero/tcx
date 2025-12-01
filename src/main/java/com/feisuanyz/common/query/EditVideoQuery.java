package com.feisuanyz.common.query;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   编辑视频信息查询参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditVideoQuery {
    @NotNull(message = "视频ID不能为空")
    private Long videoId;

    private String title;
    private String description;
    private Long categoryId;
    private Integer visibility;
    private List<Long> tagIds;
}