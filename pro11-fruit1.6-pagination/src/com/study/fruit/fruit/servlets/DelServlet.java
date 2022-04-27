package com.study.fruit.fruit.servlets;

import com.study.fruit.fruit.dao.FruitDAO;
import com.study.fruit.fruit.dao.impl.FruitDAOImpl;
import com.study.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.study.fruit.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author RenAshbell
 * @create 2022-04-26-16:52
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidstr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidstr)){
            int fid = Integer.parseInt(fidstr);
            fruitDAO.delFruit(fid);
        }
        resp.sendRedirect("index");
    }
}
