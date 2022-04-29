package com.study.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author RenAshbell
 * @create 2022-04-29-16:01
 */
@WebFilter("*.do")
public class Filter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("B");
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("B2");
    }

    @Override
    public void destroy() {

    }
}
