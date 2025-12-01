package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.VideoCategoryDTO;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoCategoryService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *   视频分类控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/video/categories")
public class VideoCategoryController {

    private final VideoCategoryService videoCategoryService;

    @Autowired
    public VideoCategoryController(VideoCategoryService videoCategoryService) {
        this.videoCategoryService = videoCategoryService;
    }

    @GetMapping
    public RestResult<List<VideoCategoryDTO>> getAllCategories() {
        return videoCategoryService.getAllCategories();
    }
}