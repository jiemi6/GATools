package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.db.ConfigHandler;
import com.minkey.dto.JSONMessage;
import com.minkey.util.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 证书管理接口
 */
@RestController
@RequestMapping("/license")
public class LicenseController {
    private final static Logger logger = LoggerFactory.getLogger(LicenseController.class);

    @Autowired
    ConfigHandler configHandler;

    final String LICENSEKEY = "licenseKey";

    final String configKey = ConfigEnum.LicenseData.getConfigKey();

    /**
     * 导入证书,证书是根据注册码生成的,反向验证
     * @return
     */
    @RequestMapping("/up")
    public String  upFile(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("start: 上传证书文件 file: {} ",file.getName());
        try{
            String str = IOUtils.toString(file.getInputStream(), "utf-8");
            logger.debug("license : {}" ,str);

            Map<String, Object> configData = configHandler.query(configKey);
            //得到数据库中的licenseKey
            String licenseKey = (String) configData.get(LICENSEKEY);

            //校验证书与key的关系，用key解密
            String licenseData = null;

            if(StringUtils.isEmpty(licenseData)){
                //解密失败，返回错误
                return JSONMessage.createFalied("无效证书文件").toString();
            }

            //记录到数据库中
            configData.put("licenseData",licenseData);
            configHandler.insert(configKey, JSONObject.toJSONString(configData));


            return JSONMessage.createSuccess().toString();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            logger.info("end: 上传证书文件");
        }

    }

    /**
     * 获取注册码，根据硬件环境生成，重复生成是一致的
     * @return
     */
    @RequestMapping("/key")
    public String key() {
        String licenseKey = getKey();
        return JSONMessage.createSuccess().addData(LICENSEKEY,licenseKey).toString();

    }

    private String getKey(){
        String licenseKey = null;
        Map<String, Object> configData = configHandler.query(configKey);
        if(MapUtils.isNotEmpty(configData) && configData.get(LICENSEKEY) != null){
            licenseKey = configData.get(LICENSEKEY).toString();
        }else{
            //生成一个key
            licenseKey = StringUtil.md5(Math.random()+"");
            configData = new HashMap<>(1);
            configData.put(LICENSEKEY,licenseKey);
            configHandler.insert(configKey, JSONObject.toJSONString(configData));
        }
        return licenseKey;
    }

    /**
     * 获取注册码文件
     * @return
     */
    @RequestMapping("/keyExport")
    public ResponseEntity<byte[]> keyExport() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=licenseKey");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        String licenseKey = getKey();

        return new ResponseEntity<>(licenseKey.getBytes(), headers, HttpStatus.CREATED);

    }



}