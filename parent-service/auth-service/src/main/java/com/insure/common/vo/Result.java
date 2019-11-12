package com.insure.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2563845153217925209L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code = 0;

    private Long total;

    /**
     * 返回数据对象 data
     */
    private T result;

    public Result() {

    }

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result(boolean success, String message, Integer code, T result) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.result = result;
    }

    public static<T> Result<T> err(String message,Integer code){
        Result<T> result = new Result<>(false,message,code,null);
        return result;
    }

    public static<T> Result<T> err500(String message){
        return Result.err(message,500);
    }

    public static<T> Result<T> succ(T data){
        Result<T> result = new Result<>(true, null, 200, data);
        return result;
    }
}
