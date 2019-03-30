package com.minkey.httpserver;

import com.alibaba.fastjson.JSONObject;
import com.minkey.command.Ping;
import com.minkey.command.SnmpUtil;
import com.minkey.command.Telnet;
import com.minkey.dto.BaseConfigData;
import com.minkey.dto.JSONMessage;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;
import com.minkey.executer.LocalExecuter;
import com.minkey.executer.SSHExecuter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Scope("prototype")
public class BaseController {

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
    public String ping(String ip,int pingTimes,double intervalTime,int timeout) {
        log.debug("exec ping [{}] start! " , ip);
        if(StringUtils.isEmpty(ip)){
            log.error("ip 参数为空！");
            return JSONMessage.createFalied("ip 参数为空！").toString();
        }

        try{
            JSONObject data = new JSONObject();
            int successNum = Ping.ping(ip,pingTimes,intervalTime,timeout);
            data.put("isConnect",successNum > 0);
            data.put("successNum",successNum);
            return JSONMessage.createSuccess().addData(data).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec ping [{}] end! " , ip);
        }
    }

    @RequestMapping("/pingConnect")
    public String pingConnect(String ip) {
        log.debug("exec pingConnect [{}] start! " , ip);
        if(StringUtils.isEmpty(ip)){
            log.error("ip 参数为空！");
            return JSONMessage.createFalied("ip 参数为空！").toString();
        }

        try{
            JSONObject data = new JSONObject();
            boolean isConnected  = Ping.pingConnect(ip);
            data.put("isConnect",isConnected);
            return JSONMessage.createSuccess().addData(data).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec pingConnect [{}] end! " , ip);
        }
    }


    /**
     * sh命令代理
     * @param cmdStr
     * @return
     */
    @RequestMapping("/executeSh")
    public String executeSh(String cmdStr) {
        log.debug("exec executeSh [{}] start! " , cmdStr);
        if(StringUtils.isEmpty(cmdStr)){
            log.error("cmdStr 参数为空！");
            return JSONMessage.createFalied("cmdStr 参数为空！").toString();
        }

        try{
            ResultInfo resultInfo = LocalExecuter.exec(cmdStr);
            return JSONMessage.createSuccess().addData(resultInfo).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec executeSh [{}] end! " , cmdStr);
        }
    }


    /**
     * 远程执行sh命令代理
     * @param cmdStr
     * @return
     */
    @RequestMapping("/executeRemoteSh")
    public String executeRemoteSh(BaseConfigData baseConfigData , String cmdStr) {
        log.debug("exec executeRemoteSh [{}] start! " , cmdStr);
        if(StringUtils.isEmpty(cmdStr)){
            log.error("cmdStr 参数为空！");
            return JSONMessage.createFalied("cmdStr 参数为空！").toString();
        }

        try{
            SSHExecuter sshExecuter = new SSHExecuter(baseConfigData);
            ResultInfo resultInfo = sshExecuter.sendCmd(cmdStr);
            return JSONMessage.createSuccess().addData(resultInfo).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec executeRemoteSh [{}] end! " , cmdStr);
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
        log.debug("exec telnetCmd [{} {}] start .",ip,port);
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
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec telnetCmd  [{} {}] end .",ip,port);
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
        log.debug("exec snmpCmdGetList [{}:{}-{}:V{}] start .",ip,port,community,version);
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
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec snmpCmdGetList [{}:{}-{}:V{}]  end .",ip,port,community,version);
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
        log.debug("exec snmpCmdWalk [{}:{}-{}:V{}] start .",ip,port,community,version);
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
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.toString()).toString();
        }finally {
            log.debug("exec snmpCmdWalk [{}:{}-{}:V{}] end .",ip,port,community,version);
        }
    }

}