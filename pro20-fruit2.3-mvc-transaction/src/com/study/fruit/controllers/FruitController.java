package com.study.fruit.controllers;

import com.study.fruit.service.FruitService;
import com.study.fruit.pojo.Fruit;
import com.study.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController {
    private FruitService fruitService = null;

    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark){
        //3.执行更新
        fruitService.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));

        //4.资源跳转
        //super.processTemplate("index",request,response);
        //request.getRequestDispatcher("index.html").forward(request,response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        return "redirect:fruit.do";
    }

    private String edit(Integer fid,HttpServletRequest request) {
        if(fid != null){
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
//            super.processTemplate("edit",request,response);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid) {
        if(fid != null){
            fruitService.delFruit(fid);

            //super.processTemplate("index",request,response);
//            response.sendRedirect("fruit.do");
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname,Integer price,Integer fcount,String remark) {
        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitService.addFruit(fruit);
//        response.sendRedirect("fruit.do");
        return "redirect:fruit.do";

    }

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request) {
        HttpSession session = request.getSession() ;
        if (pageNo == null){
            pageNo = 1 ;
        }
        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1 ;
            //如果keyword为null，需要设置为空字符串""，否则查询时会拼接成 %null% , 我们期望的是 %%
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            //将keyword保存（覆盖）到session中
            session.setAttribute("keyword",keyword);
        }else{
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            //如果不是点击的查询按钮，那么查询是基于session中保存的现有keyword进行查询
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }

        // 重新更新当前页的值
        session.setAttribute("pageNo",pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(keyword , pageNo);
        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount",pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
//        super.processTemplate("index",request,response);
        return "index";
    }
}