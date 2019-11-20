package com.insure.common.exception;

import com.insure.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO...
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Getter
@Setter
public class InsureException extends RuntimeException {

    private Integer code;

    private String msg;

    private Object result;

    public InsureException(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public InsureException(Integer code,String msg,Object result){
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public InsureException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }
}
