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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Integer pageNo = 1;

        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);

        String oper = req.getParameter("oper");
        // 如果oper!=null 说明 通过表单的按钮查询过来
        // 如果oper==null 说明 不是通过表单的查询按钮点击过来的

        String keyword = null;
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            // 说明时点击查询表单发送过来的请求
            // 此时，pageNo应该还原为1，keyword应该从请求参数中获取
            pageNo = 1;
            keyword = req.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        } else {
            // 说明此处不是点击表单查询发送过来的请求
            // 此时keyword应该从session作用域获取
            String pageNoStr = req.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        FruitDAO fruitDao = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDao.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);

        // 总记录条数
        int fruitCount = fruitDao.getFruitCount(keyword);
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
