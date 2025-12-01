package com.feisuanyz.common.exception;


/**
 * <p>
 *   用户未找到异常
 * </p>
 * @author tianchunxu
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}