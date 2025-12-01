package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.DanmakuDTO;
import com.feisuanyz.common.dto.DanmakuQuery;
import com.feisuanyz.common.response.RestResult;
import java.util.List;

/**
 * <p>
 *   弹幕服务接口
 * </p>
 * @author tianchunxu
 */
public interface DanmakuService {

    /**
     * 发送弹幕
     * @param danmakuDTO 弹幕数据传输对象
     * @return 返回结果
     */
    RestResult sendDanmaku(DanmakuDTO danmakuDTO);

    /**
     * 获取指定时间区间内的弹幕列表
     * @param danmakuQuery 弹幕查询参数封装对象
     * @return 返回结果
     */
    RestResult<List<DanmakuDTO>> getDanmakuList(DanmakuQuery danmakuQuery);
}