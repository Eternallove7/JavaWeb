package com.study.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author RenAshbell
 * @create 2022-04-22-10:17
 */
// 读取外部的配置文件，设置连接池
public class Demo05Druid {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream is = Demo05Druid.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(is);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);


        // 证明两点：
        // 1.被close的连接对象并没有真正关闭而是将状态重新设置为空闲状态，然后放回池子，下次获取连接对象，下次获取的连接对象会被重复使用
        // 2.没有close的连接对象被一直占用，那么下次继续获取连接对象，是不会获取这个连接对象的
        for (int i = 0; i < 10; i++) {
            Connection conn1 = dataSource.getConnection();
            System.out.println(i + "------------>" + conn1);

        }

    }
}
