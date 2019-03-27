package com.example.springbootdemoconsumer.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YangPeng
 * @Title: ProductService
 * @ProjectName springbootdemo
 * @Description: TODO
 * @date 2019/3/27-11:23
 */
//name 为product项目中application.yml配置文件中的application.name;
//path 为product项目中application.yml配置文件中的context.path;
@FeignClient(name = "product-server",path ="/product" )
@Component
public interface ProductService {
    @RequestMapping(value = "getProduct")
    String getProduct();
}
