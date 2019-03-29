package com.minkey.httpserver;


import com.alibaba.fastjson.JSONObject;
import com.minkey.command.SnmpUtil;
import com.minkey.contants.CommonContants;
import com.minkey.dto.*;
import com.minkey.exception.SystemException;
import com.minkey.executer.SSHExecuter;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Scope("prototype")
@RequestMapping("/test")
public class TestController {
    @Autowired
    DynamicDB dynamicDB;

    @Autowired
    FTPUtil ftpUtil;

    @RequestMapping("/testDB")
    public String testDBConnect(DBConfigData dbConfigData){
        log.debug("start: 执行测试数据库是否能连通 dbConfigData={}",dbConfigData);

        if(StringUtils.isEmpty(dbConfigData.getIp())
                || StringUtils.isEmpty(dbConfigData.getPwd())
                || StringUtils.isEmpty(dbConfigData.getName())
                || StringUtils.isEmpty(dbConfigData.getDbName())
                || dbConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            boolean isConnect = dynamicDB.testDBConnect(dbConfigData);

            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行测试数据库是否能连通 ");
        }
    }

    @RequestMapping("/testDBSource")
    public String testDBSource(DBConfigData dbConfigData){
        log.debug("start: 执行测试数据库资源测试 dbConfigData={}",dbConfigData);

        if(StringUtils.isEmpty(dbConfigData.getIp())
                || StringUtils.isEmpty(dbConfigData.getPwd())
                || StringUtils.isEmpty(dbConfigData.getName())
                || StringUtils.isEmpty(dbConfigData.getDbName())
                || dbConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            JSONObject resultJson = dynamicDB.testDBSource(dbConfigData);

            return JSONMessage.createSuccess().addData(resultJson).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 执行测试数据库资源测试 ");
        }
    }

    @RequestMapping("/testFTPConnect")
    public String testFTPConnect(FTPConfigData ftpConfigData){
        log.debug("start: 测试ftp服务器是否连通 ftpConfigData={}",ftpConfigData);

        if(StringUtils.isEmpty(ftpConfigData.getIp())
                || StringUtils.isEmpty(ftpConfigData.getPwd())
                || StringUtils.isEmpty(ftpConfigData.getName())
                || ftpConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }
        try{
            boolean isConnect = ftpUtil.testFTPConnect(ftpConfigData,CommonContants.DEFAULT_TIMEOUT);
            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 测试ftp服务器是否连通 ");
        }
    }

    @RequestMapping("/testFTPSource")
    public String testFTPSource(FTPConfigData ftpConfigData){
        log.debug("start: 测试ftp服务器情况 ftpConfigData={}",ftpConfigData);

        if(StringUtils.isEmpty(ftpConfigData.getIp())
                || StringUtils.isEmpty(ftpConfigData.getPwd())
                || StringUtils.isEmpty(ftpConfigData.getName())
                || ftpConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            JSONObject result = ftpUtil.testFTPSource(ftpConfigData,CommonContants.DEFAULT_TIMEOUT);
            return JSONMessage.createSuccess().addData(result).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 测试ftp服务器 ");
        }
    }

    @RequestMapping("/testSSH")
    public String testSSH(BaseConfigData baseConfigData){
        log.debug("start: 测试SSH服务器连接情况 baseConfigData={}",baseConfigData);

        if(StringUtils.isEmpty(baseConfigData.getIp())
                || StringUtils.isEmpty(baseConfigData.getPwd())
                || StringUtils.isEmpty(baseConfigData.getName())
                || baseConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            JSONObject result =  SSHExecuter.testSSH(baseConfigData);
            return JSONMessage.createSuccess().addData(result).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 测试SSH服务器连接情况 ");
        }
    }


    @RequestMapping("/testSNMP")
    public String testSNMP(SnmpConfigData snmpConfigData){
        log.debug("start: 测试SNMP服务器连接情况 snmpConfigData={}",snmpConfigData);

        if(StringUtils.isEmpty(snmpConfigData.getIp())
                || StringUtils.isEmpty(snmpConfigData.getCommunity())
                || snmpConfigData.getVersion() == 0
                || snmpConfigData.getPort() <= 0){

            return JSONMessage.createFalied("参数错误").toString();
        }

        try{
            SnmpUtil snmpUtil = new SnmpUtil(snmpConfigData.getIp(),snmpConfigData.getPort(),snmpConfigData.getCommunity(),snmpConfigData.getVersion(),0, CommonContants.DEFAULT_TIMEOUT);
            boolean isConnect =  snmpUtil.testConnect();
            return JSONMessage.createSuccess().addData("isConnect",isConnect).toString();
        }catch (SystemException e){
            return JSONMessage.createFalied(e.getErrorCode(),e.getMessage()).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 测试SNMP服务器连接情况 ");
        }
    }

}
