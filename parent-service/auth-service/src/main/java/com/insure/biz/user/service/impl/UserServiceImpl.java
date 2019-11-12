package com.insure.biz.user.service.impl;

import com.insure.biz.user.entity.User;
import com.insure.biz.user.mapper.UserMapper;
import com.insure.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public User getUserById(String userId) {
        return userMapper.selectUserById(userId);
    }
}
