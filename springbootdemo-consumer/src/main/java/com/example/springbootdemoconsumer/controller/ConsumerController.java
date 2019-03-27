package com.example.springbootdemoconsumer.controller;


import com.example.springbootdemoconsumer.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
    * @Title: ConsumerController
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @date 2019/3/27-11:32
*/
@RestController
public class ConsumerController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "getConsumer")
    public String getConsumer(){
       String str =  productService.getProduct();
       return str;
    }
}
