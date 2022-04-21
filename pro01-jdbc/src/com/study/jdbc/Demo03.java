package com.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author RenAshbell
 * @create 2022-04-21-15:26
 */

// JDBC - 修改和删除
public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Fruit fruit = new Fruit(33,"猕猴桃","猕猴桃是水果之王");

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "202517");
        String sql = "update t_fruit set fname = ?,remark = ? where fid = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,fruit.getFname());
        psmt.setString(2,fruit.getRemark());
        psmt.setInt(3,fruit.getFid());

        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "修改成功！" : "修改失败!");

        psmt.close();
        conn.close();

    }
}
