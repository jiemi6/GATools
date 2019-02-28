package com.minkey.util;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 短信发送接口
 */
@Slf4j
@Component
public class SendSMS {


    public void send(JSONObject smsConfig, String msg) {
        String phone = smsConfig.getString("fstel");
        if(StringUtils.isEmpty(phone)){
            return;
        }

        jm(phone,smsConfig,msg);
    }

    /**
     * 江门公安局短信接口
     *
     * @param smsConfig
     * @param msg
     */
    private void jm(String phone,JSONObject smsConfig, String msg) {
        /*
        * http://10.45.0.84/JmGajSmsWS/JmGajSmsWS.asmx（地址只能在公安网访问）
        *调用方法：
        * string strDX = dx.sendSms("Anxins!AxFSms"调用密码, strSJHM手机号, "[新OA系统]短信内容" + str MESSAGE, "000000", "新OA系统");
        * 参数:
        * invokePwd：调用密码；
        * mobile：手机号码；
        * content：短信内容；
        * svrtype：类型；
        * bz：备注;
        * eid 回复短信用,2位系统代码;
        * pid 回复短信用,11位以内流水号。
        *
        *请求报文
<?xml version="1.0" encoding="utf-8"?>
<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
  <soap12:Body>
    <sendSms xmlns="http://tempuri.org/">
      <invokePwd>string</invokePwd>
      <mobile>string</mobile>
      <content>string</content>
      <svrtype>string</svrtype>
      <bz>string</bz>
    </sendSms>
  </soap12:Body>
</soap12:Envelope>
        *
        * 返回报文
<?xml version="1.0" encoding="utf-8"?>
<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
  <soap12:Body>
    <sendSmsResponse xmlns="http://tempuri.org/">
      <sendSmsResult>string</sendSmsResult>
    </sendSmsResponse>
  </soap12:Body>
</soap12:Envelope>

        *
        * */

        String requestStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
                "  <soap12:Body>" +
                "    <sendSms xmlns=\"http://tempuri.org/\">" +
                "      <invokePwd>Anxins!AxFSms</invokePwd>" +
                "      <mobile>"+phone+"</mobile>" +
                "      <content>"+msg+"</content>" +
                "      <svrtype>000000</svrtype>" +
                "      <bz>边界跨域</bz>" +
                "    </sendSms>" +
                "  </soap12:Body>" +
                "</soap12:Envelope>";

        CloseableHttpResponse response = null ;
        try {
            // 创建httpclient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost("http://10.45.0.84/JmGajSmsWS/JmGajSmsWS.asmx");

            // 设置参数到请求对象中
            StringEntity stringEntity = new StringEntity(requestStr, ContentType.create("text/xml","utf-8"));
            stringEntity.setContentEncoding("utf-8");
            httpPost.setEntity(stringEntity);

            // 执行请求操作，并拿到结果（同步阻塞）
            response = httpClient.execute(httpPost);

            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.debug("发送短信返回报文:"+result);
            }else{
                log.warn("发送短信接口返回错误:"+response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 释放链接
            try {
                response.close();
            } catch (IOException e) {
            }
        }
    }
}
