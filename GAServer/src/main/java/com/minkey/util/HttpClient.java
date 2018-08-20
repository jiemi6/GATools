package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.minkey.exception.SystemException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;



public class HttpClient {

	public static String postRequest(String url,JSONObject param) throws SystemException {
		CloseableHttpClient client = HttpClients.createDefault(); 
		
		RequestConfig requestConfig = RequestConfig.custom()  
				//读取8秒超时
		        .setSocketTimeout(3000)  
		        //连接8秒超时
		        .setConnectTimeout(3000)
		        .setConnectionRequestTimeout(3000)
		        .build();

		// 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
		for(Entry entry :param.entrySet()){
			formparams.add(new BasicNameValuePair(entry.getKey().toString(),  entry.getValue().toString()));
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
			throw new SystemException("httpClient请求异常",e);
		}finally{
			IOUtils.close(reader);
			IOUtils.close(in);
		}
	}

}
