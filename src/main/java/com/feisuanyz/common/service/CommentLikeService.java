package com.feisuanyz.common.service;


public interface CommentLikeService {

    boolean isCommentLikedByUser(Long commentId, Long userId);

    void addCommentLike(Long commentId, Long userId);

    void removeCommentLike(Long commentId, Long userId);
}