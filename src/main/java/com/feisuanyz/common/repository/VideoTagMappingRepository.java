package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoTagMappingDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.feisuanyz.common.entity.VideoTagMapping;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   视频与标签关联Repository接口
 * </p>
 * @author tianchunxu
 */
@Repository
public interface VideoTagMappingRepository extends JpaRepository<VideoTagMappingDO, Long> {

    void deleteByVideoId(Long videoId);

    List<VideoTagMappingDO> findByVideoId(Long videoId);
}
