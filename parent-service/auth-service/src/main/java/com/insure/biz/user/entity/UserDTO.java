package com.insure.biz.user.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * TODO...
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@Data
public class UserDTO {

    private String id;

    private String username;

    private String password;

    private String name;

    private LocalDate birthday;

    private String token;
}
