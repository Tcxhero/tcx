package com.feisuanyz.common.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   后台操作日志数据传输对象
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOperationLogDTO {

    private Long id;
    private Long operatorId;
    private String operationModule;
    private String operationType;
    private Long targetId;
    private String detailInfo;
    private String ipAddress;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
}