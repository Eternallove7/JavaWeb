package com.study.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author RenAshbell
 * @create 2022-04-22-10:17
 */

public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        // 批处理操作一：如果要执行批处理任务，要添加一个参数：rewriteBatchedStatements=true
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "202517");
        String fname = "'西瓜' or 1=1 or fname=''";
        String sql = "select * from t_fruit where fname = " + fname;

        System.out.println(sql);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            System.out.println(rs.getString("fname"));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getInt("fcount"));
            System.out.println(rs.getString("remark"));
        }
    }
}
/*
Statement - 存在注入式漏洞，一般情况下不使用
    PrepareStatement - 预处理命令对象
        CallableStatement - 执行存储过程

 */