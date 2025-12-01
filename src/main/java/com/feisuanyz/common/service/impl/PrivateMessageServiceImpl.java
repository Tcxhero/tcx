package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.ResponseCode;
import com.feisuanyz.common.dto.PrivateMessageDTO;
import com.feisuanyz.common.dto.PrivateMessageQuery;
import com.feisuanyz.common.entity.DailyMessageLimit;
import com.feisuanyz.common.entity.PrivateMessage;
import com.feisuanyz.common.entity.UserProfile;
import com.feisuanyz.common.exception.BusinessException;
import com.feisuanyz.common.repository.DailyMessageLimitRepository;
import com.feisuanyz.common.repository.PrivateMessageRepository;
import com.feisuanyz.common.repository.UserProfileRepository;
import com.feisuanyz.common.service.PrivateMessageService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *   私信消息Service实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    private static final Integer MAX_MESSAGE_LIMIT = 10; // 每日最大发送条数

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DailyMessageLimitRepository dailyMessageLimitRepository;

    @Override
    public PrivateMessageDTO sendMessage(Long senderId, Long receiverId, String content) {
        UserProfile senderProfile = userProfileRepository.findByUserIdAndStatus(senderId);
        UserProfile receiverProfile = userProfileRepository.findByUserIdAndStatus(receiverId);

        if (senderProfile == null || receiverProfile == null) {
            throw new BusinessException(ResponseCode.USER_NOT_FOUND, "发送方或接收方用户不存在或状态不正常");
        }

        Date currentDate = new Date();
        DailyMessageLimit dailyLimit = dailyMessageLimitRepository.findByUserIdAndSendDate(senderId, currentDate);

        if (dailyLimit == null) {
            dailyLimit = new DailyMessageLimit(null, senderId, currentDate, 0, null, currentDate, null, currentDate);
        }

        if (dailyLimit.getMessageCount() >= MAX_MESSAGE_LIMIT) {
            throw new BusinessException(ResponseCode.MESSAGE_LIMIT_EXCEEDED, "超出当日发送上限");
        }

        PrivateMessage message = new PrivateMessage(null, senderId, receiverId, content, false, null, currentDate, null, currentDate);
        privateMessageRepository.save(message);

        dailyLimit.setMessageCount(dailyLimit.getMessageCount() + 1);
        dailyMessageLimitRepository.save(dailyLimit);

        PrivateMessageDTO messageDTO = new PrivateMessageDTO();
        BeanUtils.copyProperties(message, messageDTO);
        return messageDTO;
    }

    @Override
    public List<PrivateMessageDTO> getPrivateMessageList(Long userId, PrivateMessageQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNum() - 1, query.getPageSize());
        List<PrivateMessage> messages = privateMessageRepository.findByUserIdAndTargetUserId(userId, query.getTargetUserId());

        return messages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void markMessageAsRead(Long messageId, Long userId) {
        PrivateMessage message = privateMessageRepository.findById(messageId)
                .orElseThrow(() -> new BusinessException(ResponseCode.MESSAGE_NOT_FOUND, "消息不存在"));

        if (!message.getReceiverId().equals(userId)) {
            throw new BusinessException(ResponseCode.ILLEGAL_OPERATION, "非法操作");
        }

        message.setIsRead(true);
        privateMessageRepository.save(message);
    }

    private PrivateMessageDTO convertToDTO(PrivateMessage message) {
        PrivateMessageDTO messageDTO = new PrivateMessageDTO();
        BeanUtils.copyProperties(message, messageDTO);
        return messageDTO;
    }
}