package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.PrivateMessageDTO;
import com.feisuanyz.common.dto.PrivateMessageQuery;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   私信消息Service接口
 * </p>
 * @author tianchunxu
 */
public interface PrivateMessageService {

    @Transactional
    PrivateMessageDTO sendMessage(Long senderId, Long receiverId, String content);

    List<PrivateMessageDTO> getPrivateMessageList(Long userId, PrivateMessageQuery query);

    @Transactional
    void markMessageAsRead(Long messageId, Long userId);
}