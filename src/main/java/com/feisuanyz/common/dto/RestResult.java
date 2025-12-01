package com.feisuanyz.common.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 统一返回结果封装类
 *
 * @author tianchunxu
 */
@Data
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private T data;

    /**
     * 构造函数
     */
    public RestResult() {
    }

    public static <T> RestResult<T> success(T data) {
        RestResult<T> result = new RestResult<>();
        result.setCode("000000");
        result.setMsg("调用成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败返回结果
     *
     * @param code 错误码
     * @param msg  错误信息
     * @return RestResult
     */
    public static <T> RestResult<T> fail(String code, String msg) {
        RestResult<T> result = new RestResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> RestResult<T> failure(String code, String msg) {
        RestResult<T> result = new RestResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
