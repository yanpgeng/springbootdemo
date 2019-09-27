package com.example.springbootdemotoken.filter;

import com.example.springbootdemotoken.consts.SysConst;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public interface BaseFilter extends Filter {

    ObjectMapper JSON = new ObjectMapper();

    @Override
    void init(FilterConfig filterConfig) throws ServletException;

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    @Override
    void destroy();

    default void pushResponseError(ServletResponse response, SysConst cnt) {
        response.setContentType("text/json; charset=UTF-8");
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", cnt.getCode());
        resMap.put("data", cnt.getValue());
        resMap.put("message", cnt.getMessage());
        try {
            PrintWriter out = response.getWriter();
            out.println(JSON.writeValueAsString(resMap));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
