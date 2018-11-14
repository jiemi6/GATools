package com.minkey.util;

import com.alibaba.fastjson.util.IOUtils;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * http 请求客户端
 *
 */
@Slf4j
public class HttpClient {

	public static String postRequest(String url,Map<String,String> param) throws SystemException {
		CloseableHttpClient client = HttpClients.createDefault(); 
		
		RequestConfig requestConfig = RequestConfig.custom()  
				//读取3秒超时
		        .setSocketTimeout(30000)
		        //连接3秒超时
		        .setConnectTimeout(3000)
		        .setConnectionRequestTimeout(3000)
		        .build();

		// 创建参数队列
        List<NameValuePair> formparams = new ArrayList<>();
        if(!CollectionUtils.isEmpty(param)) {
			for (Entry<String, String> entry : param.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setConfig(requestConfig);

		UrlEncodedFormEntity uefEntity = null;  
		InputStream in = null;
		BufferedReader reader = null;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httpPost.setEntity(uefEntity);  
            HttpResponse response = client.execute(httpPost);

            HttpEntity entity = response.getEntity();
            in = entity.getContent();  
            reader = new BufferedReader(new InputStreamReader(in));
			
			StringBuffer buffer = new StringBuffer();
			while(true){
				String temp = reader.readLine();
				if(temp == null){
					break;
				}
				buffer.append(temp);
			}
			return buffer.toString();
		} catch (Exception e) {
			log.error("httpClient请求异常,"+e.getMessage());
			return null;
		}finally{
			IOUtils.close(reader);
			IOUtils.close(in);
		}
	}

}
