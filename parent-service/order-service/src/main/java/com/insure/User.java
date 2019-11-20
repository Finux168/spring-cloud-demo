package com.insure;

import lombok.Data;

import java.time.LocalDate;

/**
 * 用户实体类
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Data
public class User {

    private String id;

    private String username;

    private String password;

    private String name;

    private LocalDate birthday;
}
