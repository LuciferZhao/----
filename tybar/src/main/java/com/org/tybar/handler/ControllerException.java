package com.org.tybar.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerException {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ModelAndView exceptionHandler(HttpServletRequest res,Exception e) throws Exception {

        // 记录错误日志
        logger.error("Resquest Url : {}, Exception : {}",res.getRequestURI(),e);

        // 判断是否有状态码
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        // 跳转错误页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",res.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");

        return mv;
    }

}
