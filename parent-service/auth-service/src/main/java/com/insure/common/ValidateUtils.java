package com.insure.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 校验 {@link BindingResult} 工具类
 *
 * @Author: fgd
 * @Date: 2019-11-15
 */
public class ValidateUtils {

    public static ErrorCode invoke(BindingResult bindingResult){
        for (ObjectError error : bindingResult.getAllErrors()) {
            String message = error.getDefaultMessage();
            return ErrorCode.getErrorCodeByCode(Integer.valueOf(message));
        }
        return null;
    }
}
