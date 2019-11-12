package com.insure.common.exception;

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
}
