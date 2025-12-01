package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.constant.ErrorCode;
import com.feisuanyz.common.constant.ErrorMessage;
import com.feisuanyz.common.dto.VideoInteractionDTO;
import com.feisuanyz.common.dto.VideoInteractionQuery;
import com.feisuanyz.common.entity.VideoInteractionDO;
import com.feisuanyz.common.repository.VideoInteractionRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.VideoInteractionService;
import com.feisuanyz.common.util.UserUtil;
import com.feisuanyz.common.util.VideoUtil;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *   视频互动行为业务逻辑实现层
 * </p>
 * @author tianchunxu
 */
@Slf4j
@Service
public class VideoInteractionServiceImpl implements VideoInteractionService {

    @Autowired
    private VideoInteractionRepository videoInteractionRepository;

    @Override
    @Transactional
    public RestResult likeVideo(VideoInteractionQuery query) {
        if (!UserUtil.exists(query.getUserId())) {
            return RestResult.fail(ErrorCode.USER_NOT_EXISTS, ErrorMessage.USER_NOT_EXISTS);
        }

        if (!VideoUtil.exists(query.getVideoId())) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_EXISTS, ErrorMessage.VIDEO_NOT_EXISTS);
        }

        Optional<VideoInteractionDO> interactionOptional = videoInteractionRepository.findByUserIdAndVideoId(query.getUserId(), query.getVideoId());
        VideoInteractionDO interaction;

        if (interactionOptional.isPresent()) {
            interaction = interactionOptional.get();
            interaction.setLikeStatus(1);
        } else {
            interaction = new VideoInteractionDO();
            interaction.setUserId(query.getUserId());
            interaction.setVideoId(query.getVideoId());
            interaction.setLikeStatus(1);
            interaction.setCoinAmount(0);
            interaction.setFavoriteStatus(0);
        }

        videoInteractionRepository.save(interaction);
        return RestResult.success();
    }

    @Override
    @Transactional
    public RestResult unlikeVideo(VideoInteractionQuery query) {
        if (!UserUtil.exists(query.getUserId())) {
            return RestResult.fail(ErrorCode.USER_NOT_EXISTS, ErrorMessage.USER_NOT_EXISTS);
        }

        if (!VideoUtil.exists(query.getVideoId())) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_EXISTS, ErrorMessage.VIDEO_NOT_EXISTS);
        }

        Optional<VideoInteractionDO> interactionOptional = videoInteractionRepository.findByUserIdAndVideoId(query.getUserId(), query.getVideoId());

        if (interactionOptional.isEmpty()) {
            return RestResult.fail(ErrorCode.NOT_LIKED_VIDEO, ErrorMessage.NOT_LIKED_VIDEO);
        }

        VideoInteractionDO interaction = interactionOptional.get();
        interaction.setLikeStatus(0);

        videoInteractionRepository.save(interaction);
        return RestResult.success();
    }

    @Override
    @Transactional
    public RestResult coinVideo(VideoInteractionDTO dto) {
        if (!UserUtil.exists(dto.getUserId())) {
            return RestResult.fail(ErrorCode.USER_NOT_EXISTS, ErrorMessage.USER_NOT_EXISTS);
        }

        if (!VideoUtil.exists(dto.getVideoId())) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_EXISTS, ErrorMessage.VIDEO_NOT_EXISTS);
        }

        if (dto.getCoinAmount() <= 0) {
            return RestResult.fail(ErrorCode.INVALID_COIN_AMOUNT, ErrorMessage.INVALID_COIN_AMOUNT);
        }

        Optional<VideoInteractionDO> interactionOptional = videoInteractionRepository.findByUserIdAndVideoId(dto.getUserId(), dto.getVideoId());
        VideoInteractionDO interaction;

        if (interactionOptional.isPresent()) {
            interaction = interactionOptional.get();
            interaction.setCoinAmount(interaction.getCoinAmount() + dto.getCoinAmount());
        } else {
            interaction = new VideoInteractionDO();
            interaction.setUserId(dto.getUserId());
            interaction.setVideoId(dto.getVideoId());
            interaction.setLikeStatus(0);
            interaction.setCoinAmount(dto.getCoinAmount());
            interaction.setFavoriteStatus(0);
        }

        videoInteractionRepository.save(interaction);
        return RestResult.success();
    }

    @Override
    @Transactional
    public RestResult favoriteVideo(VideoInteractionQuery query) {
        if (!UserUtil.exists(query.getUserId())) {
            return RestResult.fail(ErrorCode.USER_NOT_EXISTS, ErrorMessage.USER_NOT_EXISTS);
        }

        if (!VideoUtil.exists(query.getVideoId())) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_EXISTS, ErrorMessage.VIDEO_NOT_EXISTS);
        }

        Optional<VideoInteractionDO> interactionOptional = videoInteractionRepository.findByUserIdAndVideoId(query.getUserId(), query.getVideoId());
        VideoInteractionDO interaction;

        if (interactionOptional.isPresent()) {
            interaction = interactionOptional.get();
            interaction.setFavoriteStatus(1);
        } else {
            interaction = new VideoInteractionDO();
            interaction.setUserId(query.getUserId());
            interaction.setVideoId(query.getVideoId());
            interaction.setLikeStatus(0);
            interaction.setCoinAmount(0);
            interaction.setFavoriteStatus(1);
        }

        videoInteractionRepository.save(interaction);
        return RestResult.success();
    }

    @Override
    @Transactional
    public RestResult unfavoriteVideo(VideoInteractionQuery query) {
        if (!UserUtil.exists(query.getUserId())) {
            return RestResult.fail(ErrorCode.USER_NOT_EXISTS, ErrorMessage.USER_NOT_EXISTS);
        }

        if (!VideoUtil.exists(query.getVideoId())) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_EXISTS, ErrorMessage.VIDEO_NOT_EXISTS);
        }

        Optional<VideoInteractionDO> interactionOptional = videoInteractionRepository.findByUserIdAndVideoId(query.getUserId(), query.getVideoId());

        if (interactionOptional.isEmpty()) {
            return RestResult.fail(ErrorCode.NOT_FAVORITED_VIDEO, ErrorMessage.NOT_FAVORITED_VIDEO);
        }

        VideoInteractionDO interaction = interactionOptional.get();
        interaction.setFavoriteStatus(0);

        videoInteractionRepository.save(interaction);
        return RestResult.success();
    }

    @Override
    public RestResult getInteractionStatus(VideoInteractionQuery query) {
        if (!UserUtil.exists(query.getUserId())) {
            return RestResult.fail(ErrorCode.USER_NOT_EXISTS, ErrorMessage.USER_NOT_EXISTS);
        }

        if (!VideoUtil.exists(query.getVideoId())) {
            return RestResult.fail(ErrorCode.VIDEO_NOT_EXISTS, ErrorMessage.VIDEO_NOT_EXISTS);
        }

        Optional<VideoInteractionDO> interactionOptional = videoInteractionRepository.findByUserIdAndVideoId(query.getUserId(), query.getVideoId());
        VideoInteractionDTO interactionDTO;

        if (interactionOptional.isPresent()) {
            VideoInteractionDO interactionDO = interactionOptional.get();
            interactionDTO = new VideoInteractionDTO();
            interactionDTO.setUserId(interactionDO.getUserId());
            interactionDTO.setVideoId(interactionDO.getVideoId());
            interactionDTO.setLikeStatus(interactionDO.getLikeStatus());
            interactionDTO.setCoinAmount(interactionDO.getCoinAmount());
            interactionDTO.setFavoriteStatus(interactionDO.getFavoriteStatus());
        } else {
            interactionDTO = new VideoInteractionDTO();
            interactionDTO.setUserId(query.getUserId());
            interactionDTO.setVideoId(query.getVideoId());
            interactionDTO.setLikeStatus(0);
            interactionDTO.setCoinAmount(0);
            interactionDTO.setFavoriteStatus(0);
        }

        return RestResult.success(interactionDTO);
    }
}