package com.shangui.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e) throws UnsupportedEncodingException {
        FastJsonJsonView view = new FastJsonJsonView();
        ModelAndView mv = new ModelAndView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (e instanceof MyException){
            attributes.put("code", ((MyException) e).getErrorCode());
            attributes.put("msg", e.getMessage());
        } else {
            attributes.put("code", 500);
            if (e.getMessage() == null) {
                attributes.put("msg", "系统错误");
            } else {
                attributes.put("msg", e.getMessage());
            }
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
