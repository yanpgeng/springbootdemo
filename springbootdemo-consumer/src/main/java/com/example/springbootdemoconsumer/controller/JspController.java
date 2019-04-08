package com.example.springbootdemoconsumer.controller;


import com.example.springbootdemoentity.config.repeatCommitConfig.AvoidRepeatableCommit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
    * @Title: JspController
    * @ProjectName springbootdemo
    * @Description: jsp跳转controller
    * @author YangPeng
    * @date 2019/4/4-17:19
    */
@Controller
public class JspController {
    @AvoidRepeatableCommit
    @RequestMapping(value = "/getJsp")
    public String  getJsp(){
        return "index";
    }
}
