package com.insure.biz.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.insure.biz.user.entity.LoginJson;
import com.insure.biz.user.entity.User;
import com.insure.biz.user.entity.UserDTO;
import com.insure.biz.user.mapper.UserMapper;
import com.insure.biz.user.service.UserService;
import com.insure.common.ErrorCode;
import com.insure.common.RedisUtil;
import com.insure.common.constant.LoginConstant;
import com.insure.common.exception.InsureException;
import com.insure.common.vo.SessionInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.UUID;

/**
 * 实现类
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getUserById(String userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public UserDTO checkUser(LoginJson loginJson) {
        User user = userMapper.selectUserByUserName(loginJson.getUsername());
        if (user == null){
            throw new InsureException(ErrorCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(loginJson.getPassword())){
            throw new InsureException(ErrorCode.USER_PASSWORD_INCORRECT);
        }
        //生成token
        String token = UUID.randomUUID().toString().replace("-","");
        //保存至redis  key为token value为SessionInfo
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserId(user.getId());
        boolean isSucc = redisUtil.set(LoginConstant.LOGIN_TOKEN_REDIS_PREFIX + token, JSONObject.toJSONString(sessionInfo),24*60*60);
        if (!isSucc){
            throw new InsureException(ErrorCode.USER_LOGIN_FAIL);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setToken(token);
        return userDTO;
    }

}
