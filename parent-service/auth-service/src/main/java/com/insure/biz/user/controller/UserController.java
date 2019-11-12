package com.insure.biz.user.controller;

import com.insure.biz.user.entity.User;
import com.insure.biz.user.service.UserService;
import com.insure.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser")
    public Result<User> getUserById(@RequestParam("id")String id){
        return Result.succ(userService.getUserById(id));
    }
}
