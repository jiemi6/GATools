package com.minkey.test.db;

import com.alibaba.fastjson.JSONObject;
import com.minkey.db.dao.DeviceService;

public class OtherTest
{
    public static void main(String[] args) {

        DeviceService data = JSONObject.parseObject("{'pwd':'dd'}",DeviceService.class);

        data = null;
    }
}
