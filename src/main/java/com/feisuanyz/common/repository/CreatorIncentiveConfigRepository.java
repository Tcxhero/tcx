package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.CreatorIncentiveConfig;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创作者激励预留配置Repository
 *
 * @author tianchunxu
 */
public interface CreatorIncentiveConfigRepository extends JpaRepository<CreatorIncentiveConfig, Long> {

    /**
     * 根据创作者ID和激励类型查询配置信息
     *
     * @param creatorId 创作者ID
     * @param incentiveType 激励类型
     * @return Optional<CreatorIncentiveConfig>
     */
    Optional<CreatorIncentiveConfig> findByCreatorIdAndIncentiveType(Long creatorId, Integer incentiveType);

    /**
     * 根据创作者ID查询所有配置信息
     *
     * @param creatorId 创作者ID
     * @return List<CreatorIncentiveConfig>
     */
    List<CreatorIncentiveConfig> findByCreatorId(Long creatorId);

    /**
     * 根据配置ID查询配置信息
     *
     * @param id 配置ID
     * @return Optional<CreatorIncentiveConfig>
     */
    Optional<CreatorIncentiveConfig> findById(Long id);
}