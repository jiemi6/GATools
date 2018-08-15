package com.minkey.httpserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @RequestMapping("/")
    public String home() {


        return "index";
    }


    @RequestMapping("/config")
    public String config() {
        //跳到跟目录下的 config
        return "config";
    }
}