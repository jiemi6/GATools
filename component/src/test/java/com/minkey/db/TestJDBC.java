package com.minkey.db;

import com.minkey.dto.DBConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DynamicDB;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class TestJDBC {

    @Test
    public void testMysql() {
        String ip = "127.0.0.1";
        int port =3306;
        String dbName = "smzdm";

        try {
            DBConfigData baseConfigData = new DBConfigData();
            baseConfigData.setIp(ip);
            baseConfigData.setPort(port);
            baseConfigData.setDbName(dbName);
            baseConfigData.setName("root1");
            baseConfigData.setPwd("root");

            JdbcTemplate jdbcTemplate = new DynamicDB().get8dbConfig(baseConfigData);

            int i = jdbcTemplate.queryForObject("select count(*) from zhi_items", Integer.class);

        }catch (SystemException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
