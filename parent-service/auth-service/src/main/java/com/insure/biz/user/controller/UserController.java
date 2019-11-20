package com.insure.biz.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.insure.biz.user.entity.LoginJson;
import com.insure.biz.user.entity.User;
import com.insure.biz.user.entity.UserDTO;
import com.insure.biz.user.service.UserService;
import com.insure.common.ErrorCode;
import com.insure.common.ValidateUtils;
import com.insure.common.constant.HeaderConstant;
import com.insure.common.exception.InsureException;
import com.insure.common.vo.Result;
import com.insure.common.vo.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Result<User> getUserById(String id){
        return Result.succ(userService.getUserById(id));
    }


    @GetMapping("getUserInfo")
    public Result<User> getUserInfo(@RequestHeader(HeaderConstant.REQUEST_SESSION)String sessionInfoStr){
        SessionInfo sessionInfo = JSONObject.parseObject(sessionInfoStr, SessionInfo.class);
        User user = userService.getUserById(sessionInfo.getUserId());
        return Result.succ(user);
    }

    /**
     * 登录接口
     *
     * @param loginJson         前端请求参数
     * @param bindingResult     固定写法
     * @return                  准确的来说,返回值应该是 userVO 的封装对象
     */
    @PostMapping("login")
    public Result<UserDTO> login(@Valid @RequestBody LoginJson loginJson, BindingResult bindingResult){
        ErrorCode code = ValidateUtils.invoke(bindingResult);
        if (code != null){
            throw new InsureException(code);
        }
        UserDTO userDTO = userService.checkUser(loginJson);
        return Result.succ(userDTO);
    }

    /**
     * 测试feign调用的接口
     *
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public Result<Boolean> updateUser(@RequestHeader(HeaderConstant.REQUEST_SESSION)String sessionInfoStr,@RequestBody User user){
        System.out.println(sessionInfoStr);
        return Result.succ(true);
    }
}
