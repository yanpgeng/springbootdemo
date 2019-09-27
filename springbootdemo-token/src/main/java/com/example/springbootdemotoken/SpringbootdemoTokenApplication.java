package com.example.springbootdemotoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan()
public class SpringbootdemoTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoTokenApplication.class, args);
    }

}
