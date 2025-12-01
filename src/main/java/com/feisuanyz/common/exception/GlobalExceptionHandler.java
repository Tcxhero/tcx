package com.feisuanyz.common.exception;

import com.feisuanyz.common.response.RestResult;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *   全局异常处理器
 * </p>
 * @author tianchunxu
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResult<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(RestResult.error("000001", "参数错误"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResult<Void>> handleException(Exception ex) {
        log.error("系统异常", ex);
        return new ResponseEntity<>(new RestResult<>(RestResult.FAILURE_CODE, "系统异常", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResult<String>> handleGeneralExceptions(Exception ex) {
        log.error("General exception occurred: ", ex);
        return new ResponseEntity<>(RestResult.error("999999", "系统错误"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
