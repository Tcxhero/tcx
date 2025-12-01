package com.feisuanyz.common.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   私信消息数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateMessageDTO {

    private Long id;

    private Long senderId;

    private Long receiverId;

    private String content;

    private Boolean isRead;

    private Date createTime;
}