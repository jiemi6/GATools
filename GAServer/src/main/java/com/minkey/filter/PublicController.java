package com.minkey.filter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用作登陆跳转
 */
@Controller
public class PublicController{

    @RequestMapping("/noLicense")
    public String noLicense() {

        return "license.html";
    }

}