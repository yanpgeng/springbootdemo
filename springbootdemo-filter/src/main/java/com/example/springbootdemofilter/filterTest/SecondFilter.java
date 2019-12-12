package com.example.springbootdemofilter.filterTest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(2)
@WebFilter(filterName="secondFilter", urlPatterns="/*")
@Component
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("second filter 1");
        System.out.println("before:" + servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("after:" + servletResponse);
        System.out.println("second filter 2");
    }

}
