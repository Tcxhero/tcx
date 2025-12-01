package com.feisuanyz.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   RestResult类，用于封装返回结果
 * </p>
 * @author tianchunxu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult {
    private String code;
    private String msg;
    private Object data;

    public static RestResult success(String msg) {
        return new RestResult("000000", msg, null);
    }

    public static RestResult success(String msg, Object data) {
        return new RestResult("000000", msg, data);
    }

    public static RestResult fail(String code, String msg) {
        return new RestResult(code, msg, null);
    }

    public static RestResult fail(String code, String msg, Object data) {
        return new RestResult(code, msg, data);
    }
}