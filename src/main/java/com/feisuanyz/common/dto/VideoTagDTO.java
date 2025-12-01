package com.feisuanyz.common.dto;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 *   视频标签数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
public class VideoTagDTO {
    private Long id;
    private String tagName;
    private Date createTime;
    private Date updateTime;
}