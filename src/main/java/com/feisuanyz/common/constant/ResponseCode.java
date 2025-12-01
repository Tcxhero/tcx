package com.feisuanyz.common.constant;


/**
 * <p>
 *   响应码常量类
 * </p>
 * @author tianchunxu
 */
public enum ResponseCode {

    SUCCESS("000000", "调用成功"),
    VIDEO_NOT_FOUND("000002", "视频未找到"),
    TITLE_TOO_LONG("000001", "视频标题过长"),
    DESCRIPTION_TOO_LONG("000001", "视频简介过长"),
    TAGS_LIMIT_EXCEEDED("000001", "标签数量超出限制"),
    INVALID_VISIBILITY("000001", "可见性设置不合法"),
    PUBLISH_TIME_REQUIRED("000001", "定时发布必须指定发布时间");

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}