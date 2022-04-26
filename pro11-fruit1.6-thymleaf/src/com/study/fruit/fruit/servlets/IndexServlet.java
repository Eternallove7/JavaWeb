package com.study.fruit.fruit.servlets;

import com.study.fruit.fruit.dao.FruitDAO;
import com.study.fruit.fruit.dao.impl.FruitDAOImpl;
import com.study.fruit.fruit.pojo.Fruit;
import com.study.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.study.fruit.myssm.util.StringUtil;

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
        Integer pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }

        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);


        FruitDAO fruitDao = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDao.getFruitList(pageNo);
        session.setAttribute("fruitList",fruitList);

        // 总记录条数
        int fruitCount = fruitDao.getFruitCount();
        // 总页数
        int pageCount = (fruitCount + 4) / 5;

        session.setAttribute("pageCount",pageCount);

        // 此处的视图名称是index
        // 那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        // 逻辑视图名称 ： index
        // 物理视图名称 ： view-prefix + 逻辑视图名称 + view-suffix
        // 真实视图名称 ：      /           index       .html
        processTemplate("index",req,resp);
    }
}
