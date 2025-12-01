package com.feisuanyz.common.service.impl;

import com.feisuanyz.common.dto.AnnouncementEditDTO;
import com.feisuanyz.common.dto.AnnouncementOfflineDTO;
import com.feisuanyz.common.dto.AnnouncementPublishDTO;
import com.feisuanyz.common.dto.SystemAnnouncementDTO;
import com.feisuanyz.common.entity.AdminOperationLog;
import com.feisuanyz.common.entity.SystemAnnouncement;
import com.feisuanyz.common.query.SystemAnnouncementQuery;
import com.feisuanyz.common.repository.AdminOperationLogRepository;
import com.feisuanyz.common.repository.SystemAnnouncementRepository;
import com.feisuanyz.common.response.RestResult;
import com.feisuanyz.common.service.SystemAnnouncementService;
import com.feisuanyz.common.util.BeanUtil;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 系统公告服务实现类
 * @author tianchunxu
 */
@Slf4j
@Service
public class SystemAnnouncementServiceImpl implements SystemAnnouncementService {

    @Resource
    private SystemAnnouncementRepository systemAnnouncementRepository;

    @Resource
    private AdminOperationLogRepository adminOperationLogRepository;

    @Override
    @Transactional
    public RestResult<?> publish(AnnouncementPublishDTO dto, Long operatorId) {
        log.info("发布系统公告，操作员ID：{}", operatorId);
        // 校验公告标题和内容是否为空
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            return RestResult.fail("公告标题或内容不能为空");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            return RestResult.fail("公告标题或内容不能为空");
        }

        // 校验公告时间范围是否合法
        if (dto.getStartTime() != null && dto.getEndTime() != null && dto.getEndTime().isBefore(dto.getStartTime())) {
            return RestResult.fail("生效结束时间不能早于开始时间");
        }

        // 构造公告对象并设置状态为上线
        SystemAnnouncement announcement = new SystemAnnouncement();
        announcement.setTitle(dto.getTitle());
        announcement.setContent(dto.getContent());
        announcement.setStartTime(dto.getStartTime());
        announcement.setEndTime(dto.getEndTime());
        announcement.setStatus(1); // 上线状态
        announcement.setCreateTime(LocalDateTime.now());
        announcement.setUpdateTime(LocalDateTime.now());
        announcement.setCreateBy(operatorId);
        announcement.setUpdateBy(operatorId);

        // 保存公告信息
        systemAnnouncementRepository.save(announcement);

        // 记录管理员操作日志
        recordOperationLog(operatorId, "SYSTEM_ANNOUNCEMENT", "PUBLISH", announcement.getId(), "发布系统公告");

        log.info("系统公告发布成功，公告ID：{}", announcement.getId());
        return RestResult.success();
    }

    @Override
    @Transactional
    public RestResult<?> edit(AnnouncementEditDTO dto, Long operatorId) {
        log.info("编辑系统公告，公告ID：{}，操作员ID：{}", dto.getId(), operatorId);
        // 校验公告是否存在
        SystemAnnouncement announcement = systemAnnouncementRepository.findById(dto.getId()).orElse(null);
        if (announcement == null) {
            return RestResult.fail("公告信息不存在");
        }

        // 校验公告标题和内容是否为空
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            return RestResult.fail("公告标题或内容不能为空");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            return RestResult.fail("公告标题或内容不能为空");
        }

        // 校验公告时间范围是否合法
        if (dto.getStartTime() != null && dto.getEndTime() != null && dto.getEndTime().isBefore(dto.getStartTime())) {
            return RestResult.fail("生效结束时间不能早于开始时间");
        }

        // 更新公告信息
        announcement.setTitle(dto.getTitle());
        announcement.setContent(dto.getContent());
        announcement.setStartTime(dto.getStartTime());
        announcement.setEndTime(dto.getEndTime());
        announcement.setUpdateTime(LocalDateTime.now());
        announcement.setUpdateBy(operatorId);

        // 保存更新后的公告信息
        systemAnnouncementRepository.save(announcement);

        // 记录管理员操作日志
        recordOperationLog(operatorId, "SYSTEM_ANNOUNCEMENT", "EDIT", announcement.getId(), "编辑系统公告");

        log.info("系统公告编辑成功，公告ID：{}", announcement.getId());
        return RestResult.success();
    }

    @Override
    @Transactional
    public RestResult<?> offline(AnnouncementOfflineDTO dto, Long operatorId) {
        log.info("下线系统公告，公告ID：{}，操作员ID：{}", dto.getId(), operatorId);
        // 校验公告是否存在
        SystemAnnouncement announcement = systemAnnouncementRepository.findById(dto.getId()).orElse(null);
        if (announcement == null) {
            return RestResult.fail("公告信息不存在");
        }

        // 更新公告状态为下线
        announcement.setStatus(2); // 下线状态
        announcement.setUpdateTime(LocalDateTime.now());
        announcement.setUpdateBy(operatorId);

        // 保存更新后的公告状态
        systemAnnouncementRepository.save(announcement);

        // 记录管理员操作日志
        recordOperationLog(operatorId, "SYSTEM_ANNOUNCEMENT", "OFFLINE", announcement.getId(), "下线系统公告");

        log.info("系统公告下线成功，公告ID：{}", announcement.getId());
        return RestResult.success();
    }

    @Override
    public RestResult<Page<SystemAnnouncementDTO>> list(SystemAnnouncementQuery query) {
        log.info("查询系统公告列表，查询参数：{}", query);
        // 构造分页参数
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getSize());

        // 查询符合条件的公告列表
        List<SystemAnnouncement> announcements;
        if (query.getStatus() != null) {
            announcements = systemAnnouncementRepository.findByStatus(query.getStatus());
        } else {
            announcements = systemAnnouncementRepository.findAll(pageable).getContent();
        }

        // 组装分页结果数据
        List<SystemAnnouncementDTO> dtoList = new ArrayList<>();
        for (SystemAnnouncement item : announcements) {
            dtoList.add(BeanUtil.copyProperties(item, SystemAnnouncementDTO.class));
        }

        Page<SystemAnnouncementDTO> resultPage = new PageImpl<>(dtoList, pageable, dtoList.size());

        log.info("系统公告列表查询成功，共 {} 条记录", resultPage.getTotalElements());
        return RestResult.success(resultPage);
    }

    @Override
    public RestResult<SystemAnnouncementDTO> detail(Long id) {
        log.info("查看公告详情，公告ID：{}", id);
        // 校验公告是否存在
        SystemAnnouncement announcement = systemAnnouncementRepository.findById(id).orElse(null);
        if (announcement == null) {
            return RestResult.fail("公告信息不存在");
        }

        // 获取公告详细信息
        SystemAnnouncementDTO dto = BeanUtil.copyProperties(announcement, SystemAnnouncementDTO.class);

        log.info("系统公告详情查询成功，公告ID：{}", id);
        return RestResult.success(dto);
    }

    /**
     * 记录管理员操作日志
     * @param operatorId 操作员ID
     * @param module 操作模块
     * @param type 操作类型
     * @param targetId 目标ID
     * @param detail 操作详情
     */
    private void recordOperationLog(Long operatorId, String module, String type, Long targetId, String detail) {
        AdminOperationLog logEntry = new AdminOperationLog();
        logEntry.setOperatorId(operatorId);
        logEntry.setOperationModule(module);
        logEntry.setOperationType(type);
        logEntry.setTargetId(targetId);
        logEntry.setDetailInfo(detail);
        logEntry.setCreateTime(LocalDateTime.now());
        logEntry.setUpdateTime(LocalDateTime.now());
        adminOperationLogRepository.save(logEntry);
    }
}