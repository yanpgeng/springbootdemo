package com.example.springbootdemoconsumer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
    * @Title: ErrorController
    * @ProjectName springbootdemo
    * @Description: 测试全局异常处理controller
    * @author YangPeng
    * @date 2019/3/27-18:07
    */
@RestController
public class ErrorController {
    @RequestMapping(value = "/getErrorMsg")
    public String getErrorMsg(Integer n,Integer m){
       int i = n/m;
        System.out.println(i);
        return i+"";
    }
}
