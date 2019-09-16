package com.example.springbootdemorabbitmq.rabbitMQDemo01.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
    * @Title: ConnectionUtil
    * @ProjectName springbootdemo
    * @Description: 获取RabbitMQ连接工具类
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/5/28-10:43
    */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //设置端口号
        factory.setPort(5672);
        //设置账号信息、用户名、密码、vhost信息；
        factory.setVirtualHost("/admin_yangpeng");
        factory.setUsername("yangpeng");
        factory.setPassword("1qaz@WSX");
        //获取连接
        Connection connection = factory.newConnection();
        return connection;
    }


    /**
     * 测试连接RabbitMQ是否成功
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Connection con = getConnection();
        System.out.println(con);
    }
}
