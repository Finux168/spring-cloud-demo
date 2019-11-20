package com.insure;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类 封装一些公共函数
 *
 * @Author: fgd
 * @Date: 2019-11-20
 */
public class RequestContextUtils {


    public static void responseNoLoginJson(RequestContext context){
        responseErrorJson(context,"您还没有登录!","10000");
    }

    public static void responseErrorJson(RequestContext context,String code,String message){
        context.setSendZuulResponse(false);
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("code",code);
        map.put("success",false);
        map.put("result",null);
        String value = "error";
        try {
            value = JSONObject.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.getResponse().setCharacterEncoding("utf-8");
        context.setResponseBody(value);
        context.addZuulResponseHeader("Content-Type","application/json; charset=utf-8");
    }

}
