package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.AdminOperationLogDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.feisuanyz.common.entity.AdminOperationLog;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   后台操作日志Repository接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface AdminOperationLogRepository extends JpaRepository<AdminOperationLogDO, Long> {

    List<AdminOperationLogDO> findByTargetId(Long targetId);
}
