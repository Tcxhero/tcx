package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.DailyMessageLimit;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 *   每日私信发送限制Repository接口
 * </p>
 * @author tianchunxu
 */
public interface DailyMessageLimitRepository extends JpaRepository<DailyMessageLimit, Long> {

    @Query("SELECT dml FROM DailyMessageLimit dml WHERE dml.userId = :userId AND dml.sendDate = :sendDate")
    DailyMessageLimit findByUserIdAndSendDate(@Param("userId") Long userId, @Param("sendDate") Date sendDate);
}