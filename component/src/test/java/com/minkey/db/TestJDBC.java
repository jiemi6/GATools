package com.minkey.db;

import com.alibaba.fastjson.JSONObject;
import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
public class TestJDBC {

    @Test
    public void testMysql() {
        DynamicDB dynamicDB = new DynamicDB();
        String ip = "127.0.1.2";
        int port = 3306;
        String dbName = "smzdm";
        try {

            DBConfigData baseConfigData = new DBConfigData();
            baseConfigData.setIp(ip);
            baseConfigData.setPort(port);
            baseConfigData.setDbName(dbName);
            baseConfigData.setName("testAuth");
            baseConfigData.setPwd("testAuth");

            dynamicDB.testDBConnect(baseConfigData);

                JdbcTemplate jdbcTemplate = dynamicDB.get8dbConfig(baseConfigData);


            List<Map<String, Object>> sqlRowSet =jdbcTemplate.queryForList("show grants for 'testAuth'");

                log.error("ok=" );

        } catch (SystemException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    @Test
    public void testDBSource() {
        DynamicDB dynamicDB = new DynamicDB();
        String ip = "127.0.1.2";
        int port = 3306;
        String dbName = "smzdm";
        try {

            DBConfigData baseConfigData = new DBConfigData();
            baseConfigData.setIp(ip);
            baseConfigData.setPort(port);
            baseConfigData.setDbName(dbName);
            baseConfigData.setName("testAuth");
            baseConfigData.setPwd("testAuth");

            dynamicDB.testDBConnect(baseConfigData);

            JSONObject jdbcTemplate = dynamicDB.testDBSource(baseConfigData);



            log.error("ok=" +jdbcTemplate);

        } catch (SystemException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
