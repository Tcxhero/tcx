package com.feisuanyz.common.repository;

import com.feisuanyz.common.entity.CommentLikeDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 *   评论点赞数据访问层
 * </p>
 * @author tianchunxu
 */
public interface CommentLikeRepository extends JpaRepository<CommentLikeDO, Long> {

    Optional<CommentLikeDO> findByCommentIdAndUserId(Long commentId, Long userId);
}