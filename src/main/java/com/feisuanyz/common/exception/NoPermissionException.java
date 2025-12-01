package com.feisuanyz.common.exception;


/**
 * <p>
 *   无权限异常
 * </p>
 * @author tianchunxu
 */
public class NoPermissionException extends RuntimeException {

    public NoPermissionException(String message) {
        super(message);
    }
}