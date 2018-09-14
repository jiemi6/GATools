package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.dto.JSONMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工具箱接口
 */
@RestController
@RequestMapping("/tools")
public class ToolsController {
    private final static Logger logger = LoggerFactory.getLogger(ToolsController.class);



    /**
     * ping工具
     * @return
     */
    @RequestMapping("/ping")
    public String ping() {

        return JSONMessage.createSuccess().toString();

    }


    /**
     * telnet工具
     * @return
     */
    @RequestMapping("/telnet")
    public String telnet() {

        return JSONMessage.createSuccess().toString();

    }


    /**
     * ssh工具开关
     * @return
     */
    @RequestMapping("/sshd")
    public String sshd() {

        //调用系统命令进行开关，

        return JSONMessage.createSuccess().toString();

    }


    /**
     * 设置本平台的snmp服务信息，方便其他软件读取。
     * @return
     */
    @RequestMapping("/snmp")
    public String snmp(String ip,String oid) {

        JSONObject jo = new SnmpUtil(ip).snmpWalk(oid);
        //第三方实现， 默认开启

        return JSONMessage.createSuccess().addData(jo).toString();

    }

    /**
     * 设置本平台的syslog推送目的地，发送给其他服务器的日志收集服务器
     * @return
     */
    @RequestMapping("/syslog")
    public String syslog() {



        return JSONMessage.createSuccess().toString();

    }


}