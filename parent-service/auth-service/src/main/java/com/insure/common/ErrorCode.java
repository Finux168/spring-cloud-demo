package com.insure.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码枚举类
 *
 * @Author: fgd
 * @Date: 2019-11-15
 */
public enum ErrorCode {

    USER_USERNAME_NOT_NULL(10001,"username不能为空!"),
    USER_PASSWORD_NOT_NULL(10002,"password不能为空!"),
    USER_NOT_EXIST(10003,"用户不存在!"),
    USER_PASSWORD_INCORRECT(10004,"密码错误!"),
    USER_LOGIN_FAIL(10005,"用户登录失败!")
    ;

    public static final Map<Integer, ErrorCode> MAP = new HashMap<>();

    static {
        for (ErrorCode errorCode : ErrorCode.values()){
            MAP.put(errorCode.code,errorCode);
        }
    }

    private Integer code;

    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCode getErrorCodeByCode(Integer code){
       return MAP.get(code);
    }
}
