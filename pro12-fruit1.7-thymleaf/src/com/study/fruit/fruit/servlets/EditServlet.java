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
import java.io.IOException;

/**
 * @author RenAshbell
 * @create 2022-04-26-15:36
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidstr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidstr)){
            int fid = Integer.parseInt(fidstr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit",fruit);
            super.processTemplate("edit",req,resp);
        }
    }
}
