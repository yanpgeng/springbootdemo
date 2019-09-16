package com.example.springbootdemorabbitmq.rabbitMQDemo01.entity;


import com.example.springbootdemorabbitmq.rabbitMQDemo01.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
    * @Title: SendEntity
    * @ProjectName springbootdemo
    * @Description: 生产者向消费队列发送消息
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/5/28-10:54
    */
public class ProductEntity {
    //定义一个消息队列的名称；
    private final static String QUEUE_NAME = "QUEUE_A";

        public static void main(String[] args) throws IOException {
            //获取连接及MQ通道；
            Connection connection = ConnectionUtil.getConnection();
            //从连接中创建通道
            Channel channel = connection.createChannel();
            //声明（创建）队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //消息内容
            String message = "hello world";
            //发布消息
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x]Sent'"+message+"'");
            //关闭通道和连接
            channel.close();
            connection.close();
        }
}
