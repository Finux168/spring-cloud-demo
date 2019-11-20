package com.insure.biz.user.mapper;

import com.insure.biz.user.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * dao
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
public interface UserMapper {

    User selectUserById(@Param("userId") String userId);

    User selectUserByUserName(@Param("username")String username);

}
