package com.study.fruit.servlets;

import com.study.fruit.dao.FruitDAO;
import com.study.fruit.dao.impl.FruitDAOImpl;
import com.study.fruit.pojo.Fruit;
import com.study.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author RenAshbell
 * @create 2022-04-25-11:07
 */

// servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDao = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDao.getFruitList();
        // 保存到session作用域
        HttpSession session = req.getSession();
        session.setAttribute("fruitList",fruitList);

        // 此处的视图名称是index
        // 那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        // 逻辑视图名称 ： index
        // 物理视图名称 ： view-prefix + 逻辑视图名称 + view-suffix
        // 真实视图名称 ：      /           index       .html
        processTemplate("index",req,resp);
    }
}
