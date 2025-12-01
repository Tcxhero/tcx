package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.AdminOperationLogDTO;
import com.feisuanyz.common.dto.UserProfileDTO;
import com.feisuanyz.common.exception.NoPermissionException;
import com.feisuanyz.common.exception.UserNotFoundException;
import com.feisuanyz.common.query.UserProfileQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.UserProfileService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   用户Controller类
 * </p>
 * @author tianchunxu
 */
@RestController
@RequestMapping("/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/ban")
    public RestResult<UserProfileDTO> banUser(@RequestHeader("operatorId") Long operatorId,
                                              @Valid @RequestBody UserProfileQuery query,
                                              @RequestParam("reason") String reason) {
        try {
            UserProfileDTO userProfileDTO = userProfileService.banUser(operatorId, query, reason);
            return RestResult.success(userProfileDTO);
        } catch (NoPermissionException e) {
            return RestResult.fail("000001", e.getMessage());
        } catch (UserNotFoundException e) {
            return RestResult.fail("000001", e.getMessage());
        }
    }

    @PostMapping("/unban")
    public RestResult<UserProfileDTO> unbanUser(@RequestHeader("operatorId") Long operatorId,
                                                @Valid @RequestBody UserProfileQuery query,
                                                @RequestParam("reason") String reason) {
        try {
            UserProfileDTO userProfileDTO = userProfileService.unbanUser(operatorId, query, reason);
            return RestResult.success(userProfileDTO);
        } catch (NoPermissionException e) {
            return RestResult.fail("000001", e.getMessage());
        } catch (UserNotFoundException e) {
            return RestResult.fail("000001", e.getMessage());
        }
    }

    @GetMapping("/violation-log")
    public RestResult<List<AdminOperationLogDTO>> getViolationLog(@RequestHeader("operatorId") Long operatorId,
                                                                  @Valid @RequestBody UserProfileQuery query) {
        try {
            validateAdminPermission(operatorId);
            List<AdminOperationLogDTO> logDTOs = userProfileService.getUserProfile(query.getUserId()).getViolationLogs();
            return RestResult.success(logDTOs);
        } catch (NoPermissionException e) {
            return RestResult.fail("000001", e.getMessage());
        } catch (UserNotFoundException e) {
            return RestResult.fail("000001", e.getMessage());
        }
    }

    private void validateAdminPermission(Long operatorId) {
        if (!isAdmin(operatorId)) {
            throw new NoPermissionException("权限不足");
        }
    }

    private boolean isAdmin(Long operatorId) {
        // 校验管理员权限的逻辑
        return true; // 示例中返回true，实际应用中需要实现具体的校验逻辑
    }
}