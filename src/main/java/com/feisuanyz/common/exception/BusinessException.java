package com.feisuanyz.common.exception;

import com.feisuanyz.common.constant.ResponseCode;

/**
 * <p>
 *   业务异常类
 * </p>
 * @author tianchunxu
 */
public class BusinessException extends RuntimeException {

    private String errorCode;

    private String code;

    public BusinessException(ResponseCode responseCode, String message) {
        super(message);
        this.code = responseCode.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
