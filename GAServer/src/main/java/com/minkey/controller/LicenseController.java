package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.ConfigEnum;
import com.minkey.db.ConfigHandler;
import com.minkey.dto.JSONMessage;
import com.minkey.util.StringUtil;
import com.minkey.util.SymmetricEncoder;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        logger.info("start: 上传证书文件 file: {} ",file.getOriginalFilename());
        try{
            file.getBytes();
            String str = IOUtils.toString(file.getInputStream(), "utf-8");
            str.getBytes();
            if(StringUtils.isEmpty(str)){
                //返回错误
                return JSONMessage.createFalied("无效证书文件").toString();
            }

            logger.info("license : {}" ,str);

            Map<String, Object> dbData = configHandler.query(configKey);

            if(MapUtils.isEmpty(dbData)){
                return JSONMessage.createFalied("请先生成licenseKey").toString();
            }

            JSONObject configData = JSONObject.parseObject((String) dbData.get("configData"));
            //得到数据库中的licenseKey
            String licenseKey = configData.getString(LICENSEKEY);

            //校验证书与key的关系，用key解密
            byte[] licenseData = SymmetricEncoder.AESDncode(licenseKey.getBytes(), file.getBytes());

            if(StringUtils.isEmpty(licenseData)){
                //解密失败，返回错误
                return JSONMessage.createFalied("无效证书文件").toString();
            }

            //记录到数据库中
            configData.put("licenseData",JSONObject.parse(new String(licenseData,"utf-8")));
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
        Map<String, Object> dbData = configHandler.query(configKey);
        JSONObject configData;
        if(MapUtils.isEmpty(dbData)){
            configData = new JSONObject();
        }else{

            configData = JSONObject.parseObject((String) dbData.get("configData"));
        }


        if(configData.getString(LICENSEKEY) != null){
            licenseKey = configData.getString(LICENSEKEY);
        }else{
            //生成一个key
            licenseKey = StringUtil.md5(Math.random()+"");
            configData.put(LICENSEKEY,licenseKey);
            configHandler.insert(configKey, configData.toJSONString());
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
    @RequestMapping("/licenseExport")
    public String licenseExport(String licenseKey,HttpServletResponse response) {
        logger.info("start: 根据key={}获取licenseData ",licenseKey);
        if(StringUtils.isEmpty(licenseKey)){
            //返回错误
            return JSONMessage.createFalied("参数错误").toString();
        }
        byte[] licenseData = null;

        JSONObject jo = new JSONObject();
        jo.put("data","ok");
        if(!StringUtils.isEmpty(licenseKey)){
            licenseData = SymmetricEncoder.AESEncode(licenseKey.getBytes(),jo.toJSONString().getBytes());
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=licenseData");

        try {
            response.getOutputStream().write(licenseData);
            return null;
        } catch (IOException e) {
            return null;
        }

    }



}