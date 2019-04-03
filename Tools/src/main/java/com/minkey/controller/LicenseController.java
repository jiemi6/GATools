package com.minkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.minkey.util.SymmetricEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 证书管理接口
 */
@Slf4j
@RestController
@RequestMapping("/license")
public class LicenseController {

    @Autowired
    HttpSession session;

    final String DATA_DEADLINE = "deadline";
    final String DATA_PUBLISHER = "publisher";

    /**
     * 获取license
     * @return
     */
    @RequestMapping("/licenseExport")
    public String licenseExport(String licenseKey,HttpServletResponse response) {
        log.debug("start: 根据key= {} 获取licenseData ",licenseKey);
        byte[] licenseData;
        if(StringUtils.isEmpty(licenseKey)){
            //返回错误
            licenseData = "参数错误".getBytes();
        }else{
            JSONObject jo = new JSONObject();
            jo.put(DATA_DEADLINE, "2019-06-01");
            jo.put(DATA_PUBLISHER, "江门市公安局");
            if(!StringUtils.isEmpty(licenseKey)){
                //加密
                licenseData = SymmetricEncoder.AESEncode(licenseKey.getBytes(),jo.toJSONString().getBytes());
            }else{
                licenseData = "数据加密错误!".getBytes();
            }

            licenseData = Base64.encodeBase64(licenseData);

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=licenseData");
        }

        try {
            response.getOutputStream().write(licenseData);
            return null;
        } catch (IOException e) {
            return null;
        }
    }


}