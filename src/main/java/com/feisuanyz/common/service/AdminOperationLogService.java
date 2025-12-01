package com.feisuanyz.common.service;

import com.feisuanyz.common.entity.AdminOperationLog;

/**
 * <p>
 *   后台操作日志Service接口
 * </p>
 * @author tianchunxu
 */
public interface AdminOperationLogService {

    void logOperation(Long operatorId, String operationModule, String operationType, Long targetId, String detailInfo, String ipAddress);

    void saveAdminOperationLog(AdminOperationLog adminOperationLog);
}
