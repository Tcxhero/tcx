package com.feisuanyz.common.response;

import java.io.Serializable;
import lombok.*;

/**
 * <p>
 *   REST接口返回结果封装类
 * </p>
 * @author tianchunxu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestResult<T> {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String msg;

    public static final String FAILURE_CODE = "999999";

    public static final String SUCCESS_CODE = "000000";

    private static final long serialVersionUID = 1L;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功返回结果
     * @param data 返回数据
     * @return RestResult
     */
    public static <T> RestResult<T> success(T data) {
        return new RestResult<>("000000", "调用成功", data);
    }

    /**
     * 成功返回结果（无数据）
     * @return RestResult
     */
    public static <T> RestResult<T> success() {
        return success(null);
    }

    /**
     * 错误返回结果
     * @param code 错误码
     * @param msg 错误信息
     * @return RestResult
     */
    public static <T> RestResult<T> error(String code, String msg) {
        return new RestResult<>(code, msg, null);
    }

    public static <T> RestResult<T> fail(String code, String msg) {
        RestResult<T> result = new RestResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> RestResult<T> success(String msg) {
        return new RestResult<>("000000", msg, null);
    }

    public static RestResult success(String msg, Object data) {
        RestResult result = new RestResult();
        result.setCode("000000");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static RestResult error(String code, String msg, Object data) {
        RestResult result = new RestResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 成功响应
     * @param data 返回数据
     * @param msg 成功消息
     * @return 成功的RestResult对象
     */
    public static <T> RestResult<T> success(T data, String msg) {
        return new RestResult<>("000000", msg, data);
    }

    public static RestResult success(Object data) {
        return new RestResult("000000", "调用成功", data);
    }

    public static RestResult fail(String code, String msg, Object data) {
        return new RestResult(code, msg, data);
    }

    public static <T> RestResult<T> success(String msg, T data) {
        return new RestResult<>("000000", msg, data);
    }

    public static <T> RestResult<T> failure(String code, String msg) {
        return new RestResult<>(code, msg, null);
    }

    public static <T> RestResult<T> failure(String message) {
        RestResult<T> result = new RestResult<>();
        result.setCode("999999");
        result.setMsg(message);
        return result;
    }

    /**
     * 错误返回结果
     * @param msg 错误信息
     * @return RestResult
     */
    public static <T> RestResult<T> error(String msg) {
        return new RestResult<>("000001", msg, null);
    }
}
