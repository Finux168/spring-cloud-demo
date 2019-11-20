package com.insure.filter;

import com.insure.CommonConstant;
import com.insure.RedisUtil;
import com.insure.RequestContextUtils;
import com.insure.common.constant.HeaderConstant;
import com.insure.common.constant.LoginConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 对token进行校验
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        boolean isAccess = (boolean)currentContext.get(CommonConstant.ACCESS_NEXT_FILTER);
        return isAccess;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String tokenStr = request.getHeader(HeaderConstant.REQUEST_TOKEN);
        if (StringUtils.isEmpty(tokenStr)){
            //token校验失败,就不经过下一个过滤器
            currentContext.set(CommonConstant.ACCESS_NEXT_FILTER,false);
            RequestContextUtils.responseNoLoginJson(currentContext);
            return null;
        }
        String sessionInfoStr = (String) redisUtil.get(LoginConstant.LOGIN_TOKEN_REDIS_PREFIX + tokenStr);
        if (StringUtils.isEmpty(sessionInfoStr)){
            //token校验失败,就不经过下一个过滤器
            currentContext.set(CommonConstant.ACCESS_NEXT_FILTER,false);
            RequestContextUtils.responseNoLoginJson(currentContext);
        }
        //校验成功,需要经过下一个过滤器
        //将redis中的信息放入到请求头中,供微服务调用,类似一个全局session
        currentContext.set(CommonConstant.ACCESS_NEXT_FILTER,true);
        currentContext.addZuulRequestHeader(HeaderConstant.REQUEST_SESSION,sessionInfoStr);
        return null;
    }
}
