package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.contants.ErrorCodeEnum;
import com.minkey.contants.Modules;
import com.minkey.db.ConfigHandler;
import com.minkey.db.UserLogHandler;
import com.minkey.db.dao.User;
import com.minkey.dto.JSONMessage;
import com.minkey.util.StringUtil;
import com.minkey.util.SymmetricEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 证书管理接口
 */
@Slf4j
@RestController
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    ConfigHandler configHandler;
    @Autowired
    UserLogHandler userLogHandler;

    @Autowired
    HttpSession session;
    /**
     * config中储存的json对象真正的key所用的key
     */
    final String LICENSE_KEY = "licenseKey";
    /**
     * config中储存的json对象真正的data所用的key
     */
    final String LICENSE_DATA_KEY = "licenseData";


    final String DATA_DEADLINE = "deadline";
    final String DATA_PUBLISHER = "publisher";

    final String licenseConfigKey = ConfigEnum.LicenseData.getConfigKey();

    /**
     * 导入证书,证书是根据注册码生成的,反向验证
     * @return
     */
    @RequestMapping("/up")
    public String  upFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.debug("start: 上传证书文件 file: {} ",file.getOriginalFilename());
        try{
            //加密的原始报文
            byte[] base64Bytes = file.getBytes();

            if(ArrayUtils.isEmpty(base64Bytes)){
                //返回错误
                return JSONMessage.createFalied("无效证书文件").toString();
            }

            if(base64Bytes.length > 5*1024){
                //返回错误
                return JSONMessage.createFalied("无效证书文件").toString();
            }

            //为了得到数据库中的licenseKey
            String licenseKey = getKey();

            //得到的是未解密的证书数据
            String licenseDataStr = getLicenseDataStr(licenseKey, base64Bytes);

            if(StringUtils.isEmpty(licenseDataStr)){
                return JSONMessage.createFalied("无效证书文件").toString();
            }
            boolean isDateOk = checkLincenseDate(licenseDataStr);

            if (!isDateOk) {
                return JSONMessage.createFalied("证书文件已经过期").toString();
            }

            JSONObject configData = new JSONObject();
            configData.put(LICENSE_KEY,licenseKey);
            //把密文记录到数据库中
            configData.put(LICENSE_DATA_KEY,new String(base64Bytes,"UTF-8"));
            configHandler.insert(licenseConfigKey, JSONObject.toJSONString(configData));

            User sessionUser = (User) session.getAttribute("user");
            if(sessionUser != null){
                //记录用户日志
                userLogHandler.log(sessionUser, Modules.license,String.format("%s 导入新证书成功",sessionUser.getuName()));
            }

            this.licenseOK = true;
            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.debug("end: 上传证书文件");
        }

    }

    /**
     * 获取注册码，根据硬件环境生成，重复生成是一致的
     * @return
     */
    @RequestMapping("/key")
    public String key() {
        String licenseKey = getKey();
        return JSONMessage.createSuccess().addData(LICENSE_KEY,licenseKey).toString();

    }

    private String getKey(){
        String licenseKey = null;
        Map<String, Object> dbData = configHandler.query(licenseConfigKey);
        JSONObject configData;
        if(MapUtils.isEmpty(dbData)){
            configData = new JSONObject();
        }else{

            configData = JSONObject.parseObject((String) dbData.get(ConfigHandler.CONFIGDATAKEY));
        }


        if(configData.getString(LICENSE_KEY) != null){
            licenseKey = configData.getString(LICENSE_KEY);
        }else{
            //生成一个key
            licenseKey = StringUtil.md5(Math.random()+"");
            configData.put(LICENSE_KEY,licenseKey);
            configHandler.insert(licenseConfigKey, configData.toJSONString());
        }
        return licenseKey;
    }

    /**
     * 获取注册码key文件
     * @return
     */
    @RequestMapping("/keyExport")
    public String keyExport( HttpServletRequest request,
                           HttpServletResponse response) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=licenseKey");

        try {
            String licenseKey = getKey();
            response.getWriter().write(licenseKey);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取license
     * @return
     */
    @Deprecated
    @RequestMapping("/licenseExport")
    public String licenseExport(String licenseKey,HttpServletResponse response) {
        log.debug("start: 根据key= {} 获取Data ",licenseKey);
        if(StringUtils.isEmpty(licenseKey)){
            //返回错误
            return JSONMessage.createFalied("参数错误").toString();
        }

        return JSONMessage.createFalied("请联系供应商获取License.").toString();
//        byte[] licenseData = null;
//
//        JSONObject jo = new JSONObject();
//        jo.put(DATA_DEADLINE, "2020-01-01");
//        jo.put(DATA_PUBLISHER, "XXX有限公司");
//        if(!StringUtils.isEmpty(licenseKey)){
//            licenseData = SymmetricEncoder.AESEncode(licenseKey.getBytes(),jo.toJSONString().getBytes());
//        }
//
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition", "attachment;fileName=licenseData");
//
//        try {
//            response.getOutputStream().write(licenseData);
//            return null;
//        } catch (IOException e) {
//            return null;
//        }
    }

    /**
     * 获取licesen数据信息
     * @return
     */
    @RequestMapping("/check")
    public String check() {
        Map<String, Object> dbDataMap = configHandler.query(licenseConfigKey);

        if(MapUtils.isEmpty(dbDataMap)){
            return JSONMessage.createFalied(ErrorCodeEnum.No_license).toString();
        }

        JSONObject dbData = JSONObject.parseObject((String) dbDataMap.get(ConfigHandler.CONFIGDATAKEY));
        if(dbData == null){
            return JSONMessage.createFalied(ErrorCodeEnum.No_license).toString();
        }
        //得到的是未解密的证书数据
        String licenseValue = dbData.getString(LICENSE_DATA_KEY);
        String key = dbData.getString(LICENSE_KEY);

        //解密data,得到明文
        String licenseDataStr = getLicenseDataStr(key, licenseValue.getBytes());

        //覆盖数据库取出来的加密data
        dbData.put(LICENSE_DATA_KEY,licenseDataStr);

        return JSONMessage.createSuccess().addData(dbData).toString();
    }

    /**
     * 证书是否有效
     */
    private boolean licenseOK = false;

    /**
     * 检查证书的有效性
     * @return
     */
    public boolean checkValid() {
        return licenseOK;
    }

    /**
     * 初始化证书到缓存中
     * 每天凌晨更新一次
     */
    @Scheduled(cron="0 0 1 * * ?")
    public void init(){
        try {
            Map<String, Object> dbDataMap = configHandler.query(licenseConfigKey);

            if (MapUtils.isEmpty(dbDataMap)) {
                log.warn("数据库中证书为空!");
                return;
            }

            String configValue = (String) dbDataMap.get(ConfigHandler.CONFIGDATAKEY);
            JSONObject dbData = (JSONObject) JSONObject.parse(configValue);

            //得到的是未解密的证书数据
            String licenseValue = dbData.getString(LICENSE_DATA_KEY);
            String key = dbData.getString(LICENSE_KEY);

            String licenseDataStr = getLicenseDataStr(key, licenseValue.getBytes());

            if(StringUtils.isEmpty(licenseDataStr)){
                licenseOK = false;
            }else{
                licenseOK = checkLincenseDate( licenseDataStr);
            }

        }catch (Exception e){
            log.error("每天检查证书错误",e);
            licenseOK = false;
        }
    }

    private String getLicenseDataStr(String licenseKey , byte[] licenseValueByte){
        try {
            if(ArrayUtils.isEmpty(licenseValueByte)){
                return null;
            }

            //先解base64
            byte[] unBytes = Base64.decodeBase64(licenseValueByte);

            //用key解密,得到真正的data
            byte[] licenseByteData = SymmetricEncoder.AESDncode(licenseKey.getBytes(), unBytes);

            if(licenseByteData == null){
                //解密失败，返回错误
                return null;
            }
            //真正的data字段的值
            String licenseDataStr = new String(licenseByteData);

            return licenseDataStr;
        }catch (Exception e){
            log.error("刷新证书错误",e);
            return null;
        }

    }

    private boolean checkLincenseDate(String licenseDataStr){
        if(StringUtils.isEmpty(licenseDataStr)){
            return  false;
        }
        try {
            //真正的data字段的值
            JSONObject licenseData = JSONObject.parseObject(licenseDataStr);

            if (MapUtils.isNotEmpty(licenseData)) {
                Date dataDeadline = licenseData.getDate(DATA_DEADLINE);
                if (System.currentTimeMillis() < dataDeadline.getTime()) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            log.error("判断证书时间错误",e);
            return false;
        }

    }

}