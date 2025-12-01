package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.CommentDTO;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoCommentService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   视频评论控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/video-comment")
@Validated
public class VideoCommentController {

    @Autowired
    private VideoCommentService videoCommentService;

    @PostMapping("/add")
    public RestResult addComment(@Valid @RequestBody CommentDTO commentDTO, @RequestAttribute Long userId) {
        return videoCommentService.addComment(commentDTO, userId);
    }

    @DeleteMapping("/delete/{commentId}")
    public RestResult deleteComment(@PathVariable Long commentId, @RequestAttribute Long userId) {
        return videoCommentService.deleteComment(commentId, userId);
    }

    @PostMapping("/like/{commentId}")
    public RestResult likeComment(@PathVariable Long commentId, @RequestAttribute Long userId) {
        return videoCommentService.likeComment(commentId, userId);
    }

    @PostMapping("/unlike/{commentId}")
    public RestResult unlikeComment(@PathVariable Long commentId, @RequestAttribute Long userId) {
        return videoCommentService.unlikeComment(commentId, userId);
    }
}