package com.study.myssm.listeners;

import com.study.myssm.ioc.BeanFactory;
import com.study.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author RenAshbell
 * @create 2022-04-29-22:58
 */
// 监听上下文, 在上下文启动的时候去创建IOC容器, 然后将其保存到application
// 后面中央控制器再从application作用域中去获取IOC容器
@WebListener
public class ContextLoaderListener implements ServletContextListener{
    // 容器在启动时
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 1.获取ServletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        // 2.获取web配置文件的上下文参数contextConfigLocation
        String path = application.getInitParameter("contextConfigLocation");
        // 3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        // 4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    // 销毁时
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
