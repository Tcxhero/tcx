package com.feisuanyz.controller;

import com.feisuanyz.common.dto.PrivateMessageDTO;
import com.feisuanyz.common.dto.PrivateMessageQuery;
import com.feisuanyz.common.exception.BusinessException;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.PrivateMessageService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   私信消息Controller类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/private-messages")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @PostMapping("/send")
    public RestResult<PrivateMessageDTO> sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        try {
            PrivateMessageDTO messageDTO = privateMessageService.sendMessage(senderId, receiverId, content);
            return RestResult.success(messageDTO);
        } catch (BusinessException e) {
            return RestResult.error(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/list")
    public RestResult<List<PrivateMessageDTO>> getPrivateMessageList(@RequestParam Long userId, @Validated PrivateMessageQuery query) {
        try {
            List<PrivateMessageDTO> messageDTOs = privateMessageService.getPrivateMessageList(userId, query);
            return RestResult.success(messageDTOs);
        } catch (BusinessException e) {
            return RestResult.error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/mark-read")
    public RestResult<Void> markMessageAsRead(@RequestParam Long messageId, @RequestParam Long userId) {
        try {
            privateMessageService.markMessageAsRead(messageId, userId);
            return RestResult.success();
        } catch (BusinessException e) {
            return RestResult.error(e.getCode(), e.getMessage());
        }
    }
}