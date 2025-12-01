package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.UserProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.feisuanyz.common.entity.UserProfileDO;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 * 用户个人信息仓库接口
 *
 * @author tianchunxu
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @EntityGraph(attributePaths = { "id", "userId", "status" })
    Optional<UserProfileDO> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    @Query("SELECT up FROM UserProfile up WHERE up.userId = :userId AND up.status = 1")
    UserProfile findByUserIdAndStatus(@Param("userId") Long userId);

    @Query("SELECT u FROM UserProfile u WHERE u.nickname LIKE %?1%")
    List<UserProfile> searchUsers(String keyword);
}
