package com.feisuanyz.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 下线系统公告请求参数封装对象
 * @author tianchunxu
 */
@Data
public class AnnouncementOfflineDTO {

    /**
     * 公告ID
     */
    @NotNull(message = "公告ID不能为空")
    private Long id;
}