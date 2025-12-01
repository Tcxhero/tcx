package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.CommentDTO;
import com.feisuanyz.common.response.RestResult;

public interface VideoCommentService {

    RestResult addComment(CommentDTO commentDTO, Long userId);

    RestResult deleteComment(Long commentId, Long userId);

    RestResult likeComment(Long commentId, Long userId);

    RestResult unlikeComment(Long commentId, Long userId);
}