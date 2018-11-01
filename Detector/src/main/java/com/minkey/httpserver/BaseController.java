package com.minkey.httpserver;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.Ping;
import com.minkey.command.SnmpUtil;
import com.minkey.command.Telnet;
import com.minkey.dto.DBConfigData;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.LocalExecuter;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Scope("prototype")
public class BaseController {
    @Autowired
    DynamicDB dynamicDB;

    @RequestMapping("/check")
    public String check() {
        return JSONMessage.createSuccess().toString();
    }

    /**
     * sh命令代理
     * @param ip
     * @return
     */
    @RequestMapping("/ping")
    public String ping(String ip) {
        log.info("exec ping [{}] start! " , ip);
        if(StringUtils.isEmpty(ip)){
            log.error("ip 参数为空！");
            return JSONMessage.createFalied("ip 参数为空！").toString();
        }

        try{
            JSONObject data = new JSONObject();
            boolean isConnect = Ping.javaPing(ip);
            data.put("isConnect",isConnect);
            return JSONMessage.createSuccess().addData(data).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.info("exec ping [{}] end! " , ip);
        }
    }


    /**
     * sh命令代理
     * @param cmdStr
     * @return
     */
    @RequestMapping("/executeSh")
    public String executeSh(String cmdStr) {
        log.info("exec executeSh [{}] start! " , cmdStr);
        if(StringUtils.isEmpty(cmdStr)){
            log.error("cmdStr 参数为空！");
            return JSONMessage.createFalied("cmdStr 参数为空！").toString();
        }

        try{
            ResultInfo resultInfo = LocalExecuter.exec(cmdStr);
            return JSONMessage.createSuccess().addData(resultInfo).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.info("exec executeSh [{}] end! " , cmdStr);
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
        log.info("exec telnetCmd [{} {}] start .",ip,port);
        if(StringUtils.isEmpty(ip)){
            log.error("ip 参数为空！");
            return JSONMessage.createFalied("ip 参数为空！").toString();
        }
        if(port <= 0 ){
            log.error("port [{}] 小于 0 .",port);
            return JSONMessage.createFalied("port 参数不合法！").toString();
        }
        try{

            JSONObject data = new JSONObject();
            boolean isConnect = Telnet.doTelnet(ip, port);
            data.put("isConnect",isConnect);
            return JSONMessage.createSuccess().addData(data).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.info("exec telnetCmd  [{} {}] end .",ip,port);
        }
    }


    /**
     * snmp的getList接口
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/snmp/get")
    public String snmpCmdGet(String ip,
                             Integer port,
                             Integer version,
                             String community,
                             Integer retry,
                             Long timeout,
                             String[] oids) {
        log.info("exec snmpCmdGetList [{}:{}-{}:V{}] start .",ip,port,community,version);
        if(ArrayUtils.isEmpty(oids)){
            log.error("oids 参数为空！");
            return JSONMessage.createFalied("oids 参数不能为空").toString();
        }

        if(StringUtils.isEmpty(ip)){
            log.error("目标机器 ip 为空！");
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
            SnmpUtil snmpUtil = new SnmpUtil(ip,port,community,version,retry,timeout);

            JSONObject reData = snmpUtil.snmpGetList(oids);

            return JSONMessage.createSuccess().addData(reData).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.info("exec snmpCmdGetList [{}:{}-{}:V{}]  end .",ip,port,community,version);
        }


    }

    /**
     * snmp 的walk接口
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/snmp/walk")
    public String snmpCmdWalk(String ip,
                              Integer port,
                              Integer version,
                              String community,
                              Integer retry,
                              Long timeout,
                              String oid) {
        log.info("exec snmpCmdWalk [{}:{}-{}:V{}] start .",ip,port,community,version);
        if(StringUtils.isEmpty(oid)){
            log.error("oid 参数为空！");
            return JSONMessage.createFalied("oid 参数不能为空").toString();
        }


        if(StringUtils.isEmpty(ip)){
            log.error("目标机器 ip 为空！");
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
            SnmpUtil snmpUtil = new SnmpUtil(ip,port,community,version,retry,timeout);

            JSONObject reData = snmpUtil.snmpWalk(oid);

            return JSONMessage.createSuccess().addData(reData).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.info("exec snmpCmdWalk [{}:{}-{}:V{}] end .",ip,port,community,version);
        }
    }

    @RequestMapping("/testDB")
    public String testDB(Integer netArea, DBConfigData dbConfigData, Long detectorId){
        log.info("start: 执行测试数据库连接 netArea={},decetorId={}, dbConfigData={}",netArea,dbConfigData,dbConfigData);

        if(StringUtils.isEmpty(dbConfigData.getIp())
                || StringUtils.isEmpty(dbConfigData.getPwd())
                || StringUtils.isEmpty(dbConfigData.getName())
                || StringUtils.isEmpty(dbConfigData.getDbName())
                || dbConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect = dynamicDB.testDB(dbConfigData);

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end: 执行测试数据库连接 ");
        }
    }
}