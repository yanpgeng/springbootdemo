package com.example.springbootdemoconsumer.controller;


import com.example.springbootdemoconsumer.redisConfig.RedisConfig;
import com.example.springbootdemoconsumer.redisConfig.RedisProperties;
import com.example.springbootdemoconsumer.service.JedisClientCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
    * @Title: RedisController
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @date 2019/4/3-17:55
    */
@RestController
public class RedisController {
    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private JedisClientCluster jedisClientCluster;

    @RequestMapping(value = "getRedisValue")
    public String getRedisValue(){
        System.out.println(redisProperties.toString());
        System.out.println(redisConfig.getJedisCluster().getClusterNodes());
        System.out.println(jedisClientCluster.get("yp"));
        jedisClientCluster.set("12","12");
        System.out.println(jedisClientCluster.get("12"));
        return jedisClientCluster.get("12");
    }
}
