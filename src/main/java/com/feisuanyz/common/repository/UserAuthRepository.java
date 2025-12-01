package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.UserAuth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户认证信息仓库接口
 *
 * @author tianchunxu
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    /**
     * 根据登录类型和标识符查询用户认证信息
     *
     * @param loginType 登录类型
     * @param identifier 标识符
     * @return Optional<UserAuth>
     */
    @Query("SELECT u FROM UserAuth u WHERE u.loginType = :loginType AND u.identifier = :identifier")
    Optional<UserAuth> findByLoginTypeAndIdentifier(@Param("loginType") Integer loginType, @Param("identifier") String identifier);

    /**
     * 检查指定登录类型和标识符是否已存在
     *
     * @param loginType 登录类型
     * @param identifier 标识符
     * @return boolean
     */
    @Query("SELECT COUNT(u) > 0 FROM UserAuth u WHERE u.loginType = :loginType AND u.identifier = :identifier")
    boolean existsByLoginTypeAndIdentifier(@Param("loginType") Integer loginType, @Param("identifier") String identifier);

    /**
     * 根据用户ID和登录类型查找用户认证信息
     * @param userId 用户ID
     * @param loginType 登录类型
     * @return Optional<UserAuth>
     */
    Optional<UserAuth> findByUserIdAndLoginType(Long userId, Integer loginType);
}
