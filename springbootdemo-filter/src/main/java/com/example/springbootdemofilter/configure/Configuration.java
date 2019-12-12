//package com.example.springbootdemofilter.configure;
//
//
//import com.example.springbootdemofilter.filterTest.FirstFilter;
//import com.example.springbootdemofilter.filterTest.SecondFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//
//@org.springframework.context.annotation.Configuration
//public class Configuration {
//    @Bean
//    public FilterRegistrationBean FirstFilterRegister() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        //注入过滤器
//        registration.setFilter(new FirstFilter());
//        //拦截规则
//        registration.addUrlPatterns("/*");
//        //过滤器名称
//        registration.setName("firstFilter");
//        //过滤器顺序
//        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
//        return registration;
//    }
//    @Bean
//    public FilterRegistrationBean SecondFilterRegister() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        //注入过滤器
//        registration.setFilter(new SecondFilter());
//        //拦截规则
//        registration.addUrlPatterns("/*");
//        //过滤器名称
//        registration.setName("secondFilter");
//        //过滤器顺序
//        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
//        return registration;
//    }
//}
