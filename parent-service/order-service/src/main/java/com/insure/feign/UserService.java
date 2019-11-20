package com.insure.feign;

import com.insure.User;
import com.insure.common.constant.HeaderConstant;
import com.insure.common.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 调用鉴权服务
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
//name 需要跟 eureka中的调用服务同名
@FeignClient(name = "auth-service")
public interface UserService {

    //调用auth-service中提供的 updateUser 接口
    @PostMapping("/user/updateUser")
    Result<Boolean> changeUserInfo(@RequestHeader(HeaderConstant.REQUEST_SESSION) String sessionInfoStr,
                                   @RequestBody User user);
}
