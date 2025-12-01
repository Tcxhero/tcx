package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.StatusConstants;
import com.feisuanyz.common.constant.VideoCommentConstants;
import com.feisuanyz.common.dto.CommentDTO;
import com.feisuanyz.common.entity.VideoCommentDO;
import com.feisuanyz.common.repository.VideoCommentRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.CommentLikeService;
import com.feisuanyz.common.service.SensitiveWordService;
import com.feisuanyz.common.service.VideoCommentService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   视频评论业务逻辑实现
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class VideoCommentServiceImpl implements VideoCommentService {

    @Autowired
    private VideoCommentRepository videoCommentRepository;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Autowired
    private CommentLikeService commentLikeService;

    @Transactional
    @Override
    public RestResult addComment(CommentDTO commentDTO, Long userId) {
        if (userId == null) {
            return RestResult.fail("000001", "用户未登录");
        }

        if (commentDTO.getContent().isEmpty()) {
            return RestResult.fail("000001", "评论内容不能为空");
        }

        Optional<VideoCommentDO> parentComment = Optional.empty();
        if (commentDTO.getParentId() != null) {
            parentComment = videoCommentRepository.findById(commentDTO.getParentId());
            if (parentComment.isEmpty()) {
                return RestResult.fail("000001", "父评论不存在");
            }
            if (parentComment.get().getDepthLevel() >= VideoCommentConstants.MAX_DEPTH_LEVEL) {
                return RestResult.fail("000001", "评论层级超过限制");
            }
        }

        String filteredContent = sensitiveWordService.filterSensitiveWords(commentDTO.getContent());
        if (!filteredContent.equals(commentDTO.getContent())) {
            return RestResult.fail("000001", "评论内容包含敏感词");
        }

        VideoCommentDO newComment = new VideoCommentDO();
        newComment.setVideoId(commentDTO.getVideoId());
        newComment.setParentId(commentDTO.getParentId());
        newComment.setReplyToId(commentDTO.getReplyToId());
        newComment.setCommenterId(userId);
        newComment.setContent(filteredContent);
        newComment.setLikeCount(0);
        newComment.setStatus(StatusConstants.COMMENT_STATUS_NORMAL);
        newComment.setCreateBy(userId);
        newComment.setCreateTime(LocalDateTime.now());
        newComment.setUpdateBy(userId);
        newComment.setUpdateTime(LocalDateTime.now());

        if (parentComment.isPresent()) {
            newComment.setDepthLevel(parentComment.get().getDepthLevel() + 1);
            newComment.setFloorNumber(parentComment.get().getFloorNumber() + 1);
        } else {
            newComment.setDepthLevel(1);
            newComment.setFloorNumber(1);
        }

        videoCommentRepository.save(newComment);
        return RestResult.success("评论发表成功");
    }

    @Transactional
    @Override
    public RestResult deleteComment(Long commentId, Long userId) {
        if (userId == null) {
            return RestResult.fail("000001", "用户未登录");
        }

        Optional<VideoCommentDO> comment = videoCommentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return RestResult.fail("000001", "评论不存在");
        }

        if (!comment.get().getCommenterId().equals(userId)) {
            return RestResult.fail("000001", "无权限删除该评论");
        }

        comment.get().setStatus(StatusConstants.COMMENT_STATUS_DELETED);
        comment.get().setUpdateBy(userId);
        comment.get().setUpdateTime(LocalDateTime.now());

        videoCommentRepository.save(comment.get());
        return RestResult.success("评论删除成功");
    }

    @Transactional
    @Override
    public RestResult likeComment(Long commentId, Long userId) {
        if (userId == null) {
            return RestResult.fail("000001", "用户未登录");
        }

        Optional<VideoCommentDO> comment = videoCommentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return RestResult.fail("000001", "评论不存在");
        }

        if (commentLikeService.isCommentLikedByUser(commentId, userId)) {
            return RestResult.fail("000001", "已点赞该评论");
        }

        commentLikeService.addCommentLike(commentId, userId);
        comment.get().setLikeCount(comment.get().getLikeCount() + 1);
        comment.get().setUpdateBy(userId);
        comment.get().setUpdateTime(LocalDateTime.now());

        videoCommentRepository.save(comment.get());
        return RestResult.success("点赞成功");
    }

    @Transactional
    @Override
    public RestResult unlikeComment(Long commentId, Long userId) {
        if (userId == null) {
            return RestResult.fail("000001", "用户未登录");
        }

        Optional<VideoCommentDO> comment = videoCommentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return RestResult.fail("000001", "评论不存在");
        }

        if (!commentLikeService.isCommentLikedByUser(commentId, userId)) {
            return RestResult.fail("000001", "未点赞该评论");
        }

        commentLikeService.removeCommentLike(commentId, userId);
        comment.get().setLikeCount(comment.get().getLikeCount() - 1);
        comment.get().setUpdateBy(userId);
        comment.get().setUpdateTime(LocalDateTime.now());

        videoCommentRepository.save(comment.get());
        return RestResult.success("取消点赞成功");
    }
}