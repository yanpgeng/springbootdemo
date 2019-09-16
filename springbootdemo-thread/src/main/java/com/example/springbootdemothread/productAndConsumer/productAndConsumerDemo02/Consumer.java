package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo02;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YangPeng
 * @Title: Consumer
 * @ProjectName springbootdemo
 * @Description: TODO
 * @company ccxcredit
 * @date 2019/5/20-17:38
 */
public class Consumer implements Runnable {
    //定义参数；
    private ArrayBlockingQueue<Product> productArrayBlockingQueue;
    private AtomicInteger count;
    public Consumer(ArrayBlockingQueue<Product> productArrayBlockingQueue,AtomicInteger count) {
        this.productArrayBlockingQueue = productArrayBlockingQueue;
        this.count = count;
    }


    //消费者消费产品
    @Override
    public void run() {
        try {
            //判断容器中是否还有产品，如果没有，则该线程进行wait();
            while (count.intValue() == 0) {
                this.wait();
            }
            //如果容器中还有产品，则进行消费；
            Product product = productArrayBlockingQueue.poll();
            //容器中产品个数减一
            count.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + "消费的产品为：" + product.toString() + ",当前容器中还有产品个数为；" + count.decrementAndGet());
            //睡觉5s
            Thread.sleep(5000);
            //唤醒其他线程；
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
