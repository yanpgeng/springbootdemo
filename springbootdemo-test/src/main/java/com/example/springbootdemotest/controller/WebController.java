package com.example.springbootdemotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping("filterTest")
    public String filterTest(){
        return "fiterTest success";
    }
}
