package com.feisuanyz.common.exception;

import com.feisuanyz.common.response.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>
 *   弹幕相关异常处理
 * </p>
 * @author tianchunxu
 */
@Slf4j
@ControllerAdvice
public class DanmakuException {

    /**
     * 处理DanmakuException异常
     * @param e DanmakuException异常
     * @return 响应实体
     */
    @ExceptionHandler(DanmakuException.class)
    public ResponseEntity<RestResult<Void>> handleDanmakuException(DanmakuException e) {
        log.error("DanmakuException: {}", e.getMessage());
        return new ResponseEntity<>(RestResult.fail(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}