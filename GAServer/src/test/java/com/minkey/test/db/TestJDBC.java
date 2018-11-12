package com.minkey.test.db;

import com.minkey.util.DynamicDB;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestJDBC {

    public static void main(String[] args) {
        String ip = "222.125.67.251";
        int port =8806;
        String dbName = "smzdm";
        String url = "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8";


        JdbcTemplate jdbcTemplate = new DynamicDB().getMysqlJdbcTemplate(url, DatabaseDriver.MYSQL,"root","root");

        int i  = jdbcTemplate.queryForObject("select count(*) from zhi_items",Integer.class);

        System.out.println(i);
    }
}
