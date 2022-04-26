package com.study.fruit.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author RenAshbell
 * @create 2022-04-26-10:48
 */
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取application保存作用域保存的数据,key为uname
        ServletContext application = req.getServletContext();
        Object uname = application.getAttribute("uname");
        System.out.println("unameObj=" + uname);
    }
}
