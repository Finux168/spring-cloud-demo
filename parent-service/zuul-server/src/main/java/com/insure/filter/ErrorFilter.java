package com.insure.filter;

import com.alibaba.fastjson.JSONObject;
import com.insure.RequestContextUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

/**
 * 错误过滤器
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
@Component
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() throws ZuulException {
        //对错误格式进行统一处理
        try {
            RequestContext context = RequestContext.getCurrentContext();
            Throwable throwable = context.getThrowable();

            Map<String,Object> map = new HashMap<>();
            map.put("message",throwable.getMessage());
            map.put("code","500");
            map.put("success",false);
            map.put("result","");
            String value = "error";
            try {
                value = JSONObject.toJSONString(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpServletResponse response = context.getResponse();
            response.setContentType("application/json; charset=utf8");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print(value);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
            //打印异常
            throwable.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
