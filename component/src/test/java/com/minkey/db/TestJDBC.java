package com.minkey.db;

import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestJDBC {

    @Test
    public void testMysql() {
        DynamicDB dynamicDB = new DynamicDB();
        String ip = "127.0.1.2";
        int port = 3306;
        String dbName = "smzdm";
        while (true) {
            try {
                Thread.sleep(5000);

                DBConfigData baseConfigData = new DBConfigData();
                baseConfigData.setIp(ip);
                baseConfigData.setPort(port);
                baseConfigData.setDbName(dbName);
                baseConfigData.setName("root");
                baseConfigData.setPwd("root");



                dynamicDB.testDBConnect(baseConfigData);

//                JdbcTemplate jdbcTemplate = dynamicDB.get8dbConfig(baseConfigData);
//
//                int i = jdbcTemplate.queryForObject("select count(*) from zhi_items", Integer.class);
//
//                log.error("ok=" + i);

            } catch (SystemException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
