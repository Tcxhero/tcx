package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.PrivateMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 *   私信消息Repository接口
 * </p>
 * @author tianchunxu
 */
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {

    @Query("SELECT pm FROM PrivateMessage pm WHERE (pm.senderId = :userId AND pm.receiverId = :targetUserId) OR (pm.senderId = :targetUserId AND pm.receiverId = :userId) ORDER BY pm.createTime DESC")
    List<PrivateMessage> findByUserIdAndTargetUserId(@Param("userId") Long userId, @Param("targetUserId") Long targetUserId);

    @Query("SELECT pm FROM PrivateMessage pm WHERE pm.receiverId = :userId ORDER BY pm.createTime DESC")
    List<PrivateMessage> findByReceiverId(@Param("userId") Long userId);
}