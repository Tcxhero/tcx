package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   查询视频参数封装对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoQuery {

    @NotNull(message = "视频ID不能为空")
    private Long videoId;
}