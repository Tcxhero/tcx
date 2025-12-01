package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.PendingVideoDTO;
import com.feisuanyz.common.dto.RestResult;
import com.feisuanyz.common.query.PendingVideoQuery;
import com.feisuanyz.common.service.PendingVideoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   后台内容审核管理控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/admin/video")
public class VideoAdminController {
    @Autowired
    private PendingVideoService pendingVideoService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/pending")
    public RestResult<List<PendingVideoDTO>> getPendingVideoList(@Validated PendingVideoQuery query) {
        return pendingVideoService.getPendingVideoList(query);
    }

    @PostMapping("/approve")
    public RestResult<String> approveVideo(@RequestParam Long videoId) {
        return pendingVideoService.approveVideo(videoId);
    }

    @PostMapping("/reject")
    public RestResult<String> rejectVideo(@RequestParam Long videoId, @RequestParam String reason) {
        return pendingVideoService.rejectVideo(videoId, reason);
    }

    @PostMapping("/take-down")
    public RestResult<String> takeDownVideo(@RequestParam Long videoId, @RequestParam String reason) {
        return pendingVideoService.takeDownVideo(videoId, reason);
    }

    // 模拟获取客户端IP地址
    private String getClientIpAddress() {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}