package com.feisuanyz.common.controller;

import com.feisuanyz.common.dto.DynamicFeedDTO;
import com.feisuanyz.common.dto.DynamicFeedQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.DynamicFeedService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   动态信息流表控制层
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/dynamic-feed")
public class DynamicFeedController {

    @Autowired
    private DynamicFeedService dynamicFeedService;

    @GetMapping("/list")
    public RestResult<List<DynamicFeedDTO>> getDynamicFeedList(@Validated DynamicFeedQuery query) {
        List<DynamicFeedDTO> dynamicFeeds = dynamicFeedService.getDynamicFeedList(query);
        if (dynamicFeeds.isEmpty()) {
            return RestResult.success("无更多数据", dynamicFeeds);
        }
        return RestResult.success("调用成功", dynamicFeeds);
    }
}