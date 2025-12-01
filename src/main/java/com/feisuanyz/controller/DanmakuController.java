package com.feisuanyz.controller;

import com.feisuanyz.common.dto.DanmakuDTO;
import com.feisuanyz.common.dto.DanmakuQuery;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.DanmakuService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   弹幕控制器
 * </p>
 * @author tianchunxu
 */
@Slf4j
@RestController
@RequestMapping("/danmaku")
public class DanmakuController {

    @Autowired
    private DanmakuService danmakuService;

    /**
     * 发送弹幕
     * @param danmakuDTO 弹幕数据传输对象
     * @return 返回结果
     */
    @PostMapping("/send")
    public RestResult sendDanmaku(@Validated @RequestBody DanmakuDTO danmakuDTO) {
        return danmakuService.sendDanmaku(danmakuDTO);
    }

    /**
     * 获取指定时间区间内的弹幕列表
     * @param danmakuQuery 弹幕查询参数封装对象
     * @return 返回结果
     */
    @GetMapping("/list")
    public RestResult<List<DanmakuDTO>> getDanmakuList(@Validated DanmakuQuery danmakuQuery) {
        return danmakuService.getDanmakuList(danmakuQuery);
    }
}