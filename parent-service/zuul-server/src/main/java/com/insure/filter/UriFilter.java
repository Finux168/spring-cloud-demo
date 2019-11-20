package com.insure.filter;

import com.insure.CommonConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


/**
 * url过滤器,首先对需要校验的url进行过滤
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@Component
public class UriFilter extends ZuulFilter {

    static List<String> viaStartURIList = Arrays.asList(
            "/auth/user/login"
    );

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //url是否放行
        for (String uri : viaStartURIList) {
            if (request.getRequestURI().startsWith(uri)){
                //成功匹配就不经过下一个过滤器
                currentContext.set(CommonConstant.ACCESS_NEXT_FILTER,false);
                return null;
            }
        }
        currentContext.set(CommonConstant.ACCESS_NEXT_FILTER,true);
        return null;
    }
}
