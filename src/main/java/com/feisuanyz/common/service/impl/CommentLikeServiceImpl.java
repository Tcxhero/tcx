package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.entity.CommentLikeDO;
import com.feisuanyz.common.exception.BusinessException;
import com.feisuanyz.common.repository.CommentLikeRepository;
import com.feisuanyz.common.service.CommentLikeService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   评论点赞业务逻辑实现
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Override
    public boolean isCommentLikedByUser(Long commentId, Long userId) {
        return commentLikeRepository.findByCommentIdAndUserId(commentId, userId).isPresent();
    }

    @Transactional
    @Override
    public void addCommentLike(Long commentId, Long userId) {
        CommentLikeDO commentLike = new CommentLikeDO();
        commentLike.setCommentId(commentId);
        commentLike.setUserId(userId);
        commentLike.setCreateBy(userId);
        commentLike.setCreateTime(LocalDateTime.now());
        commentLike.setUpdateBy(userId);
        commentLike.setUpdateTime(LocalDateTime.now());

        commentLikeRepository.save(commentLike);
    }

    @Transactional
    @Override
    public void removeCommentLike(Long commentId, Long userId) {
        CommentLikeDO commentLike = commentLikeRepository.findByCommentIdAndUserId(commentId, userId)
                .orElseThrow(() -> new BusinessException("000001", "未找到点赞记录"));
        commentLikeRepository.delete(commentLike);
    }
}