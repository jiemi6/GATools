package com.minkey.spring.mvc;

import com.minkey.dto.JSONMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyExceptionHandler {

    public static final String ERROR_VIEW = "my_exception";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Exception e)throws Exception{
        e.printStackTrace();
        if(isAjax(request)){
            //是ajax请求
            return JSONMessage.createFalied(e.getMessage()).toString();
        }else{//不是ajax请求
            ModelAndView mv = new ModelAndView();
            mv.addObject("exception",e);
            mv.addObject("url",request.getRequestURL());//发生异常的路径
            mv.setViewName(ERROR_VIEW);//指定发生异常之后跳转页面
            return mv;
        }
    }

    /**
     * 判断request是否是ajax请求
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( request.getHeader("X-Requested-With").toString()) );
    }
}