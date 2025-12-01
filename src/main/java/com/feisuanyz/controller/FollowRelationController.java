package com.feisuanyz.controller;

import com.feisuanyz.common.dto.FollowListRequestDTO;
import com.feisuanyz.common.dto.FollowRequestDTO;
import com.feisuanyz.common.service.FollowRelationService;
import com.feisuanyz.common.util.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   用户关注关系Controller类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/follow")
public class FollowRelationController {
    @Autowired
    private FollowRelationService followRelationService;

    @PostMapping("/followUser")
    public RestResult followUser(@Validated @RequestBody FollowRequestDTO requestDTO) {
        return followRelationService.followUser(requestDTO);
    }

    @PostMapping("/unfollowUser")
    public RestResult unfollowUser(@Validated @RequestBody FollowRequestDTO requestDTO) {
        return followRelationService.unfollowUser(requestDTO);
    }

    @GetMapping("/getFollowList")
    public RestResult getFollowList(@Validated FollowListRequestDTO requestDTO) {
        return followRelationService.getFollowList(requestDTO);
    }

    @GetMapping("/getFollowerList")
    public RestResult getFollowerList(@Validated FollowListRequestDTO requestDTO) {
        return followRelationService.getFollowerList(requestDTO);
    }
}