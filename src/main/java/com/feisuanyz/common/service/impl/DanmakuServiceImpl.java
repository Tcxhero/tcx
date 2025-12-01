package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.ErrorCode;
import com.feisuanyz.common.dto.DanmakuDTO;
import com.feisuanyz.common.dto.DanmakuQuery;
import com.feisuanyz.common.entity.Danmaku;
import com.feisuanyz.common.entity.VideoInfo;
import com.feisuanyz.common.repository.DanmakuRepository;
import com.feisuanyz.common.repository.VideoInfoRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.DanmakuService;
import com.feisuanyz.common.util.DanmakuUtil;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   弹幕服务实现类
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class DanmakuServiceImpl implements DanmakuService {

    @Autowired
    private DanmakuRepository danmakuRepository;

    @Autowired
    private VideoInfoRepository videoInfoRepository;

    @Autowired
    private DanmakuUtil danmakuUtil;

    /**
     * 发送弹幕
     * @param danmakuDTO 弹幕数据传输对象
     * @return 返回结果
     */
    @Override
    @Transactional
    public RestResult sendDanmaku(DanmakuDTO danmakuDTO) {
        // 校验用户是否已登录
        if (!isUserLoggedIn()) {
            return RestResult.fail(ErrorCode.USER_NOT_LOGGED_IN, "用户未登录");
        }

        // 校验视频是否存在
        Optional<VideoInfo> videoInfo = videoInfoRepository.findById(danmakuDTO.getVideoId());
        if (videoInfo.isEmpty()) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        // 敏感词检测
        if (danmakuUtil.containsSensitiveWord(danmakuDTO.getContent())) {
            return RestResult.fail(ErrorCode.DANMAKU_CONTAINS_SENSITIVE_WORD, "弹幕内容包含敏感词");
        }

        // 保存弹幕信息
        Danmaku danmaku = new Danmaku();
        danmaku.setVideoId(danmakuDTO.getVideoId());
        danmaku.setContent(danmakuDTO.getContent());
        danmaku.setPlayTimeSecond(danmakuDTO.getPlayTimeSecond());
        danmaku.setColor(danmakuDTO.getColor());
        danmaku.setPosition(danmakuDTO.getPosition());
        danmakuRepository.save(danmaku);

        return RestResult.success("调用成功");
    }

    /**
     * 获取指定时间区间内的弹幕列表
     * @param danmakuQuery 弹幕查询参数封装对象
     * @return 返回结果
     */
    @Override
    public RestResult<List<DanmakuDTO>> getDanmakuList(DanmakuQuery danmakuQuery) {
        // 校验视频是否存在
        Optional<VideoInfo> videoInfo = videoInfoRepository.findById(danmakuQuery.getVideoId());
        if (videoInfo.isEmpty()) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_FOUND, "视频不存在");
        }

        // 查询该时间段内所有有效的弹幕记录
        List<Danmaku> danmakuList = danmakuRepository.findByVideoIdAndPlayTimeSecondBetween(
                danmakuQuery.getVideoId(),
                danmakuQuery.getStartTime(),
                danmakuQuery.getEndTime()
        );

        // 转换为DTO
        List<DanmakuDTO> danmakuDTOList = danmakuList.stream()
                .map(danmaku -> new DanmakuDTO(
                        danmaku.getVideoId(),
                        danmaku.getContent(),
                        danmaku.getPlayTimeSecond(),
                        danmaku.getColor(),
                        danmaku.getPosition()
                ))
                .toList();

        return RestResult.success(danmakuDTOList, "调用成功");
    }

    /**
     * 模拟用户登录状态检查
     * @return 是否已登录
     */
    private boolean isUserLoggedIn() {
        // 这里应该调用实际的用户认证服务
        return true;
    }
}