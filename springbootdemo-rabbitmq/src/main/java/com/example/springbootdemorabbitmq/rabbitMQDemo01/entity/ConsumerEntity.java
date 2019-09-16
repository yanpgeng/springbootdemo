package com.example.springbootdemorabbitmq.rabbitMQDemo01.entity;


import com.example.springbootdemorabbitmq.rabbitMQDemo01.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * @author YangPeng
 * @Title: ConsumerEntity
 * @ProjectName springbootdemo
 * @Description: TODO
 * @company ccxcredit
 * @date 2019/5/28-11:12
 */
public class ConsumerEntity {
    private final static String QUEUE_NAME = "QUEUE_A";

    public static void main(String[] args) throws IOException, InterruptedException {
        //获取到连接及MQ通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列；
        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
