package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.entity.AdminOperationLogDO;
import com.feisuanyz.common.repository.AdminOperationLogRepository;
import com.feisuanyz.common.service.AdminOperationLogService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.feisuanyz.common.entity.AdminOperationLog;

/**
 * <p>
 *   后台操作日志Service实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class AdminOperationLogServiceImpl implements AdminOperationLogService {

    @Autowired
    private AdminOperationLogRepository adminOperationLogRepository;

    @Override
    public void logOperation(Long operatorId, String operationModule, String operationType, Long targetId, String detailInfo, String ipAddress) {
        AdminOperationLogDO logDO = new AdminOperationLogDO();
        logDO.setOperatorId(operatorId);
        logDO.setOperationModule(operationModule);
        logDO.setOperationType(operationType);
        logDO.setTargetId(targetId);
        logDO.setDetailInfo(detailInfo);
        logDO.setIpAddress(ipAddress);
        logDO.setCreateBy(operatorId);
        logDO.setCreateTime(new Date());
        logDO.setUpdateBy(operatorId);
        logDO.setUpdateTime(new Date());
        adminOperationLogRepository.save(logDO);
    }

    @Override
    public void saveAdminOperationLog(AdminOperationLog adminOperationLog) {
        adminOperationLogRepository.save(adminOperationLog);
        log.info("Admin operation log saved: {}", adminOperationLog);
    }
}
