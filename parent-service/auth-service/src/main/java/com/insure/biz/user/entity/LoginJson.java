package com.insure.biz.user.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 前端请求过来的json
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@Data
public class LoginJson {

    /** message的值为 {@link com.insure.common.ErrorCode} 中的错误码 */
    @NotEmpty(message = "10000")
    private String username;

    @NotEmpty(message = "10001")
    private String password;
}
