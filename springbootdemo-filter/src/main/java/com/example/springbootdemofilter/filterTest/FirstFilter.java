package com.example.springbootdemofilter.filterTest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)
@WebFilter(filterName="firstFilter", urlPatterns="/*")
@Component
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("first filter 1");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("first filter 2");
    }

    @Override
    public void destroy() {

    }
}
