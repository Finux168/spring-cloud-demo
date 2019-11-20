package com.insure.filter;

import com.insure.CommonConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * 后置过滤器,可以用于记录日志等信息
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        boolean isAccess = (boolean)currentContext.get(CommonConstant.ACCESS_NEXT_FILTER);
        return isAccess;
    }

    @Override
    public Object run() throws ZuulException {
        //log记录
        return null;
    }
}
