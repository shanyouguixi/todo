package com.shangui.config.shiro;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.shangui.common.exception.MyException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (ex instanceof UnauthenticatedException) {
            attributes.put("code", 1001);
            attributes.put("msg", "token错误");
        } else if (ex instanceof UnauthorizedException) {
            attributes.put("code", 1002);
            attributes.put("msg", "用户无权限，请联系管理员或退出重新登陆");
        } else if (ex instanceof MyException) {
            attributes.put("code", 1003);
            Integer statusCode = (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
            Throwable t = (Throwable) httpServletRequest.getAttribute("javax.servlet.error.exception");
            String e = null;
            if (t != null) {
                e = t.getCause().getMessage();
            } else {
                e = "未知错误";
            }
            if (statusCode == 404) {
                attributes.put("msg", "请确认方法名是否正确");
            } else if (statusCode == 500) {
                attributes.put("msg", e);
            } else if (statusCode == 400) {
                attributes.put("msg", "缺少参数");
            } else {
                attributes.put("msg", "其他错误异常");
            }
        } else {
            attributes.put("code", 1003);
            attributes.put("msg", "业务错误");
        }
//        System.out.println(ex.getMessage());
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
