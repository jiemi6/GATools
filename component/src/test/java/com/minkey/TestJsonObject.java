package com.minkey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.minkey.dto.JSONMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestJsonObject {

    @Test
    public void josnstr(){
        String jsonStr = "{\"code\":10}";
        JSONMessage jo = JSON.parseObject(jsonStr,JSONMessage.class);
        JSONObject jo2 =  JSONObject.parseObject(jsonStr);
        JSONMessage jo3 = JSON.parseObject(jsonStr,new TypeReference<JSONMessage>() {});
//        log.error(""+jo.getCode());
        log.error(jo.toString());

    }


}
