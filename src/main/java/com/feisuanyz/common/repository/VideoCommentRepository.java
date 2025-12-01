package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.VideoCommentDO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 *   视频评论数据访问层
 * </p>
 * @author tianchunxu
 */
public interface VideoCommentRepository extends JpaRepository<VideoCommentDO, Long> {

    @EntityGraph(attributePaths = {"commenterId"})
    Optional<VideoCommentDO> findById(Long id);

    @EntityGraph(attributePaths = {"commenterId"})
    List<VideoCommentDO> findByVideoIdAndParentIdAndReplyToId(Long videoId, Long parentId, Long replyToId);

    @EntityGraph(attributePaths = {"commenterId"})
    List<VideoCommentDO> findByVideoIdAndParentId(Long videoId, Long parentId);

    @EntityGraph(attributePaths = {"commenterId"})
    List<VideoCommentDO> findByVideoId(Long videoId);
}