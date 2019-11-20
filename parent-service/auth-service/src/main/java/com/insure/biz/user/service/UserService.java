package com.insure.biz.user.service;

import com.insure.biz.user.entity.LoginJson;
import com.insure.biz.user.entity.User;
import com.insure.biz.user.entity.UserDTO;

/**
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
public interface UserService {

    User getUserById(String userId);

    User getUserByUsername(String username);

    UserDTO checkUser(LoginJson loginJson);
}
