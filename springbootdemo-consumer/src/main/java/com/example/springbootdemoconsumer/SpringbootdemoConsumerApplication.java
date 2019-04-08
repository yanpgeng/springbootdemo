package com.example.springbootdemoconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
/**
 * 在多模块项目中，需要用到其他模块中的对象时，需要添加@ComponentScan注解，value指的是对应的包，
 * 这样才能把相关的对象引入过来，不然项目启动时会报错（此处的报错针对的时consumer项目启动时，redis报错，无法注入，
 * 加入该注解即可将redis注入到到该module中；
 */
@ComponentScan(value = "com.example.*")
public class SpringbootdemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoConsumerApplication.class, args);
    }

}
