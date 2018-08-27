package com.minkey.controller;

import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 证书管理接口
 */
@RestController
@RequestMapping("/lincense")
public class LincenseController {
    private final static Logger logger = LoggerFactory.getLogger(LincenseController.class);



    /**
     * 导入证书,证书是根据注册码生成的,反向验证
     * @return
     */
    @RequestMapping("/upFile")
    public String upFile() {

        return JSONMessage.createSuccess().toString();

    }


    /**
     * 获取注册码，根据硬件环境生成，重复生成是一致的
     * @return
     */
    @RequestMapping("/getCode")
    public String getCode() {

        return JSONMessage.createSuccess().toString();

    }

}