package com.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author RenAshbell
 * @create 2022-04-21-10:45
 */
public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.通过驱动管理器获取连接对象
        // url 表示和数据库通信的地址
        // 如果url中需要带参数，则需要使用?进行连接
        // 如果需要多个参数，则从第二个参数开始使用?连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "202517");
        // 3.编写sql语句
        // id,fname,price,fcount,remark
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        // 4.创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        // 填充参数
        psmt.setString(1,"榴莲");
        psmt.setInt(2,15);
        psmt.setInt(3,100);
        psmt.setString(4,"榴莲是一种神器的水果");
        // 6.执行更新(增删改)，返回影响行数
        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "添加成功！" : "添加失败！");
        // 7.释放资源(关闭连接，先关闭psmt，再关闭conn)
        psmt.close();
        conn.close();
    }
}
