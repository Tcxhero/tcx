package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoCategoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   视频分类数据访问层
 * </p>
 * @author tianchunxu
 */
@Repository
public interface VideoCategoryRepository extends JpaRepository<VideoCategoryEntity, Long> {

    @EntityGraph(attributePaths = {"createBy", "updateBy"})
    List<VideoCategoryEntity> findAllByOrderBySortOrderAsc();
}