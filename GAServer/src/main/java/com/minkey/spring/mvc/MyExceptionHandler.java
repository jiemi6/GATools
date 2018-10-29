package com.minkey.spring.mvc;

import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Exception e)throws Exception{
        String requestURI =  request.getRequestURI();
        String errorMsg = String.format("执行%s异常:%s",requestURI,e.getMessage());

        logger.error(errorMsg,e);

        //未捕获异常，也处理成json返回
        return JSONMessage.createFalied(errorMsg).toString();


//        if(isAjax(request)){
//            //是ajax请求
//            return JSONMessage.createFalied(e.getMessage()).toString();
//        }else{//不是ajax请求
//            ModelAndView mv = new ModelAndView();
//            mv.addObject("exception",e);
//            mv.addObject("url",request.getRequestURL());//发生异常的路径
//            mv.setViewName("my_exception");//指定发生异常之后跳转页面
//            return mv;
//        }
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