package com.insure.common.exception;

import com.insure.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Slf4j
@RestControllerAdvice
public class InsureExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.err500(e.getMessage());
    }
}
