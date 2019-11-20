package com.insure.controller;

import com.insure.User;
import com.insure.common.constant.HeaderConstant;
import com.insure.common.vo.Result;
import com.insure.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO...
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @GetMapping("createOrder")
    public Result<String> createOrder(@RequestHeader(HeaderConstant.REQUEST_SESSION)String sessionInfoStr){
        //只是为了测试feign的调用,简单的写了一下
        User user = new User();
        user.setId("1");
        user.setUsername("hahaha");
        String string = userService.changeUserInfo(sessionInfoStr, user).toString();
        return Result.succ(string);
    }
}
