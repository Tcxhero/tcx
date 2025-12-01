package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoTagDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.feisuanyz.common.entity.VideoTag;

/**
 * <p>
 *   视频标签Repository接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface VideoTagRepository extends JpaRepository<VideoTagDO, Long> {

    @Query("SELECT v FROM VideoTag v WHERE v.tagName LIKE %?1%")
    List<VideoTag> searchTags(String keyword);

    List<VideoTagDO> findByIdIn(List<Long> tagIds);
}
