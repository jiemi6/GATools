package com.minkey.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Slf4j
@Controller
public class PageController {

    @RequestMapping("/")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        log.debug("跳转至index");
        return "forward:/index.html";
    }

//    @RequestMapping("/to_login")
//    public String onLogin(HashMap<String, Object> map) {
//        map.put("hello", "欢迎进入HTML页面");
//        log.warn("进入controller-login");
//        return "/login";
//    }
//
//    @RequestMapping("/to_license")
//    public String noLicense() {
//        log.warn("进入controller-no_license");
//        return "/license";
//    }

}
