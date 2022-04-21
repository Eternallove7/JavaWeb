package com.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author RenAshbell
 * @create 2022-04-21-10:19
 */

// 目标：和数据库建立连接
public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.添加jar
        // 2.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 3.通过驱动管理器获取连接对象
        // 3-1.准备url
        String url = "jdbc:mysql://localhost:3306/fruitdb";
        // 3-2.准备user
        String user = "root";
        // 3-3.准备密码
        String pwd = "202517";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        System.out.println(connection);

    }
}
