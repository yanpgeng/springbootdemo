package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo01;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
    * @Title: ProducerAndConsumserTest
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/5/20-16:24
    */
public class ProducerAndConsumserTest {
        public static void main(String[] args) {
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            Storage storage = new Storage();
            Consumer consumer1 = new Consumer("消费者1",storage);
//            Consumer consumer2 = new Consumer("消费者2",storage);
//            Consumer consumer3 = new Consumer("消费者3",storage);

            Producer producer1 = new Producer("生产者1",storage);
            Producer producer2 = new Producer("生产者2",storage);
            Producer producer3 = new Producer("生产者3",storage);
            Producer producer4 = new Producer("生产者4",storage);
            Producer producer5 = new Producer("生产者5",storage);
            executorService.submit(consumer1);
//            executorService.submit(consumer2);
//            executorService.submit(consumer3);

            executorService.submit(producer1);
            executorService.submit(producer2);
            executorService.submit(producer3);
            executorService.submit(producer4);
            executorService.submit(producer5);


        }
}
