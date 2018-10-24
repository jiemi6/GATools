package com.minkey.spring.mvc;

import com.minkey.dto.JSONMessage;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *系统全局异常处理器
 */
@Controller
public class MyErrorController  implements ErrorController {

    public MyErrorController() {
    }

//    @RequestMapping("/error")
    public String error() {
        System.out.println("ErrControlller.error");
        return "my_error"; // 不使用/error， 可以返回其他的， 但是要保证这个视图存在， 比如存在 public/err.html
    }

    @RequestMapping("/error")
    public Object index(Map<String,Object> map, HttpServletRequest request, HttpServletResponse response){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servlet_name = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String message = (String) request.getAttribute("javax.servlet.error.message");

        if(isAjax(request)){
            //是ajax请求
            return JSONMessage.createFalied(statusCode + ":" + message).toString();
        }else{
            //不是ajax请求
            ModelAndView mv = new ModelAndView();
            mv.addObject("error",message);
            mv.addObject("url",request.getRequestURL());//发生异常的路径
            mv.setViewName("my_error");//指定发生异常之后跳转页面
            return mv;
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";

    }

    private boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( request.getHeader("X-Requested-With").toString()) );
    }
}