package com.feisuanyz.common.query;

import lombok.Data;

/**
 * 系统公告查询参数封装对象
 * @author tianchunxu
 */
@Data
public class SystemAnnouncementQuery {

    /**
     * 公告状态
     */
    private Integer status;

    /**
     * 页码，默认1
     */
    private Integer page = 1;

    /**
     * 每页条数，默认10
     */
    private Integer size = 10;
}