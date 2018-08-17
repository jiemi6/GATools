package com.minkey.httpserver;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.Telnet;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController {
    Logger logger = LoggerFactory.getLogger(BaseController.class);
    @RequestMapping("/")
    public String home() {

        return "index";
    }


    /**
     * sh命令代理
     * @param cmdStr
     * @return
     */
    @RequestMapping("/executeSh")
    public String executeSh(String cmdStr) {
        if(StringUtils.isEmpty(cmdStr)){
            return JSONMessage.createFalied("cmdStr 参数为空！").toString();
        }
        try{
            ResultInfo resultInfo = LocalExecuter.exec(cmdStr);
            return JSONMessage.createSuccess().addData(resultInfo).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }
    }


    /**
     * telnet 命令代理
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/telnetCmd")
    public String telnetCmd(String ip,int port) {
        if(StringUtils.isEmpty(ip)){
            return JSONMessage.createFalied("cmdStr 参数为空！").toString();
        }
        if(port <= 0 ){
            return JSONMessage.createFalied("port 参数不合法！").toString();
        }
        try{
            JSONObject data = new JSONObject();
            boolean isConnect = Telnet.doTelnet(ip, port);
            data.put("isConnect",isConnect);
            return JSONMessage.createSuccess().addData(data).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }
    }


    /**
     *
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/snmpCmd")
    public String snmpCmd(String ip, int port, int version, String community, List<String> oids) {

        if(CollectionUtils.isEmpty(oids)){

        }


        return null;
    }

}