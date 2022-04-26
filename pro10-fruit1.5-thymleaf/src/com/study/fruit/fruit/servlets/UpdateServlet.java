package com.study.fruit.fruit.servlets;

import com.study.fruit.fruit.dao.FruitDAO;
import com.study.fruit.fruit.dao.impl.FruitDAOImpl;
import com.study.fruit.fruit.pojo.Fruit;
import com.study.fruit.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author RenAshbell
 * @create 2022-04-26-16:12
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置编码
        req.setCharacterEncoding("utf-8");

        // 2.获取参数
        String findstr = req.getParameter("fid");
        int fid = Integer.parseInt(findstr);
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        // 3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        // 4.资源跳转
        // 这里相当于服务器内部转发
//        super.processTemplate("index",req,resp);
        // req.getRequestDispatcher("index.html").forward(req,resp)
        // 此处需要重定向，目的是重写给IndexServlet发请求，重写获取fruitList，然后覆盖到session中，这样index.html页面上显示的session数据才是最新的
         resp.sendRedirect("index");

    }
}
