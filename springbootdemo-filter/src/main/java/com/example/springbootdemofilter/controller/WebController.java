package com.example.springbootdemofilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @RequestMapping(value = "/filterTest")
    public String filterTest(){
        return "filter test success";
    }
}
