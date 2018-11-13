package com.minkey.db;

import com.minkey.dto.DBConfigData;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class TestJDBC {

    @Test
    public void testMysql() {
        String ip = "222.125.67.251";
        int port =8806;
        String dbName = "smzdm";

        DBConfigData baseConfigData = new DBConfigData();
        baseConfigData.setIp(ip);
        baseConfigData.setPort(port);
        baseConfigData.setDbName(dbName);
        baseConfigData.setName("root");
        baseConfigData.setPwd("root");

        JdbcTemplate jdbcTemplate = new DynamicDB().get8dbConfig(baseConfigData);

        int i  = jdbcTemplate.queryForObject("select count(*) from zhi_items",Integer.class);

        System.out.println(i);
    }
}
