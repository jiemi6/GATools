package com.minkey.httpserver;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.command.Telnet;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Scope("prototype")
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
        logger.info("exec executeSh [{}] start! " , cmdStr);
        if(StringUtils.isEmpty(cmdStr)){
            logger.error("cmdStr 参数为空！");
            return JSONMessage.createFalied("cmdStr 参数为空！").toString();
        }

        try{
            ResultInfo resultInfo = LocalExecuter.exec(cmdStr);
            return JSONMessage.createSuccess().addData(resultInfo).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("exec executeSh [{}] end! " , cmdStr);
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
        logger.info("exec telnetCmd [{} {}] start .",ip,port);
        if(StringUtils.isEmpty(ip)){
            logger.error("ip 参数为空！");
            return JSONMessage.createFalied("ip 参数为空！").toString();
        }
        if(port <= 0 ){
            logger.error("port [{}] 小于 0 .",port);
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
        }finally {
            logger.info("exec telnetCmd  [{} {}] end .",ip,port);
        }
    }


    /**
     * snmp的getList接口
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/snmpCmd/getList")
    public String snmpCmdGet(String ip,
                             Integer port,
                             Integer version,
                             String community,
                             Integer retry,
                             Long timeout,
                             String[] oids) {
        logger.info("exec snmpCmdGetList [{}:{}-{}:V{}] start .",ip,port,community,version);
        if(ArrayUtils.isEmpty(oids)){
            logger.error("oids 参数为空！");
            return JSONMessage.createFalied("oids 参数不能为空").toString();
        }

        if(StringUtils.isEmpty(ip)){
            logger.error("目标机器 ip 为空！");
            return JSONMessage.createFalied("目标机器 ip 为空").toString();
        }

        if(port == null || port <=0 ){
            port = SnmpUtil.DEFAULT_PORT;
        }
        if(version== null || version <= 0){
            version = SnmpUtil.DEFAULT_VERSION;
        }
        if(StringUtils.isEmpty(community)){
            community = SnmpUtil.DEFAULT_COMMUNITY;
        }
        if(retry == null || retry <= 0 ){
            retry = SnmpUtil.DEFAULT_RETRY;
        }
        if(timeout == null ||timeout <= 0){
            timeout = SnmpUtil.DEFAULT_TIMEOUT;
        }

        try{
            CommunityTarget communityTarget = SnmpUtil.createTarget(ip,port,community,version,retry,timeout);

            JSONObject reData = SnmpUtil.snmpGetList(communityTarget,oids);

            return JSONMessage.createSuccess().addData(reData).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("exec snmpCmdGetList [{}:{}-{}:V{}]  end .",ip,port,community,version);
        }


    }

    /**
     * snmp 的walk接口
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/snmpCmd/walk")
    public String snmpCmdWalk(String ip,
                              Integer port,
                              Integer version,
                              String community,
                              Integer retry,
                              Long timeout,
                              String oid) {
        logger.info("exec snmpCmdWalk [{}:{}-{}:V{}] start .",ip,port,community,version);
        if(StringUtils.isEmpty(oid)){
            logger.error("oid 参数为空！");
            return JSONMessage.createFalied("oid 参数不能为空").toString();
        }


        if(StringUtils.isEmpty(ip)){
            logger.error("目标机器 ip 为空！");
            return JSONMessage.createFalied("目标机器 ip 为空").toString();
        }

        if(port == null || port <=0 ){
            port = SnmpUtil.DEFAULT_PORT;
        }
        if(version== null || version <= 0){
            version = SnmpUtil.DEFAULT_VERSION;
        }
        if(StringUtils.isEmpty(community)){
            community = SnmpUtil.DEFAULT_COMMUNITY;
        }
        if(retry == null || retry <= 0 ){
            retry = SnmpUtil.DEFAULT_RETRY;
        }
        if(timeout == null ||timeout <= 0){
            timeout = SnmpUtil.DEFAULT_TIMEOUT;
        }
        try{
            CommunityTarget communityTarget = SnmpUtil.createTarget(ip,port,community,version,retry,timeout);

            JSONObject reData = SnmpUtil.snmpWalk(communityTarget,oid);

            return JSONMessage.createSuccess().addData(reData).toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            logger.info("exec snmpCmdWalk [{}:{}-{}:V{}] end .",ip,port,community,version);
        }
    }

}