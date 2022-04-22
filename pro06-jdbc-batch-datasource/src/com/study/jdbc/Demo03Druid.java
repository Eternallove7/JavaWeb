package com.study.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author RenAshbell
 * @create 2022-04-22-10:17
 */
// 验证连接池的各项参数：初始化大小，最大激活数量，最大等待时间
public class Demo03Druid {
    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("202517");

        dataSource.setInitialSize(2);
        dataSource.setMaxActive(5);
        dataSource.setMaxWait(5000);


        // 证明两点：
        // 1.被close的连接对象并没有真正关闭而是将状态重新设置为空闲状态，然后放回池子，下次获取连接对象，下次获取的连接对象会被重复使用
        // 2.没有close的连接对象被一直占用，那么下次继续获取连接对象，是不会获取这个连接对象的
        for (int i = 0; i < 10; i++) {
            Connection conn1 = dataSource.getConnection();
            System.out.println(i + "------------>" + conn1);

        }

    }
}
