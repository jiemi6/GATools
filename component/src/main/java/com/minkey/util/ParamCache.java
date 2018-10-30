package com.minkey.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用缓存,线程安全.
 * 包括参数缓存和数据缓存两部分
 * @author minkey
 *
 */
@Slf4j
public class ParamCache {
	private static final String LOCAL_STR = "local";
	/**
	 * 配置文件缓存
	 */
	private static ConcurrentHashMap<String, HashMap<String, String>> params = new ConcurrentHashMap<String, HashMap<String,String>>(5);

	/**
	 * 默认本地配置
	 * @param key
	 * @return
	 */
	public static String getLocalParam(String key){
		if(StringUtils.isEmpty(key)){
			return null;
		}
		String value = null;
		HashMap<String, String> configs = params.get(LOCAL_STR);
		if(MapUtils.isNotEmpty(configs)){
			value = configs.get(key);
			if(!StringUtils.isEmpty(value)){
				return value;
			}

		}
			//配置文件中娶不到就从jvm系统参数中取得
		value = System.getProperty(key);
		if(!StringUtils.isEmpty(value)){
			return value;
		}

		log.warn("get local config is empty! key["+key+"]");
		return value;
	}


	/**
	 * 获取的指定配置文件,从zookeeper上加载的配置文件
	 * @param configName 配置名称
	 * @param key 配置key
	 * @return
	 */
	public static String getParam(String configName ,String key){
		HashMap<String, String> configs = params.get(configName);
		if(MapUtils.isEmpty(configs)){
			log.warn("get cloud config is empty! configName["+configName+"]key["+key+"]");
			return null;
		}
		String v = configs.get(key);
		if(StringUtils.isEmpty(key)){
			log.warn("get cloud config is empty! configName["+configName+"]key["+key+"]为空!");
		}
		return v;
	}

	/**
	 * 重新加载配置文件
	 * @param configName
	 * @param jo
	 */
	public static void setParam(String configName, JSONObject jo) {
		HashMap<String, String> newConfigMap = new HashMap<String, String>(5);

		for(Object key : jo.keySet()){
			if(jo.get(key) == null){
				continue;
			}
			newConfigMap.put(key.toString(), jo.get(key).toString().trim());
			log.info("download config from cloud. "+key+"="+jo.get(key));
		}
		params.put(configName, newConfigMap);
	}

	/**
	 * 放置本地基础配置文件
	 * @param props
	 */
	public static void setLocal(Properties props) {
		HashMap<String, String> localConfigMap = new HashMap<String, String>(3);
		for (Object key : props.keySet()) {
			if(props.get(key) == null || props.get(key).toString().isEmpty()){
				continue;
			}
			localConfigMap.put(key.toString(), props.get(key).toString().trim());
			log.info("load local config. "+key+"="+props.get(key));
		}
		if(params.containsKey(LOCAL_STR)){
			params.get(LOCAL_STR).putAll(localConfigMap);
		}else{
			params.put(LOCAL_STR, localConfigMap);
		}
	}



}
