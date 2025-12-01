package com.feisuanyz.common.dto;

import java.util.Date;
import lombok.Data;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   用户信息数据传输对象
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileDTO {

    private Long id;

    private Long userId;

    private String nickname;

    private String avatarUrl;

    private Integer gender;

    private Date birthday;

    private String bio;

    private Integer status;

    private Date createTime;

    private Long createBy;

    private Long updateBy;

    // 添加字段存储相关的操作日志
    private List<AdminOperationLogDTO> violationLogs;

    private Date updateTime;
}
