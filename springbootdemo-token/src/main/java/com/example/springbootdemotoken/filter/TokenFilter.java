package com.example.springbootdemotoken.filter;


import com.example.springbootdemotoken.consts.SysConst;
import com.example.springbootdemotoken.extend.BodyReaderHttpRequestWrapper;
import com.example.springbootdemotoken.tokens.JsonWebToken;
import com.example.springbootdemotoken.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
    * @Title: TokenFilter
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/9/27-11:18
    */
@WebFilter
public class TokenFilter implements BaseFilter{

    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private static List<String> WHITEURLS = new ArrayList<>();
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 读取文件
        try {

            if (!SysConst.DEV.getValue().equals(config.getInitParameter("level"))) {
                InputStream stream = getClass().getClassLoader().getResourceAsStream("static/tokenWhite.txt") ;
                WHITEURLS = FileUtils.readWhiteFile(stream);
            } else {
                logger.debug(config.getInitParameter("level"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");

        String tmp = request.getRequestURI();
        String token = null;
        request = new BodyReaderHttpRequestWrapper(request);
        if (!FileUtils.isWhiteBeg(request.getRequestURI(), WHITEURLS)) {
            token = request.getHeader("token");
            if (null == JsonWebToken.verifyToken(token)) {
                pushResponseError(response, SysConst.TOKENEXCEPTION);
                return;
            } else {

                /*
                try {
                    response.setHeader("token", JsonWebToken.updateToken(token));
                } finally {
                }*/
            }
        }

        response.setHeader("token", token);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
