package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo02;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YangPeng
 * @Title: Produce
 * @ProjectName springbootdemo
 * @Description: TODO
 * @company ccxcredit
 * @date 2019/5/20-17:35
 */
public class Produce implements Runnable {
    /**
     * 定义一个生产者线程对象；；
     */
    /**
     * 生产者线程对象中定义一个容器，将生产者生产的内容保存到容器中；
     */
    private ArrayBlockingQueue<Product> productArrayBlockingQueue;
    private AtomicInteger count;

    public Produce(ArrayBlockingQueue<Product> productArrayBlockingQueue, AtomicInteger count) {
        this.productArrayBlockingQueue = productArrayBlockingQueue;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            //一直判断容器中的size是否等于10，如果等于10则处于wait()状态，否则进行其他操作；
            while (count.intValue() == 10) {
                this.wait();
            }
            Product product = new Product(Thread.currentThread().getName(), "12");
            productArrayBlockingQueue.add(product);
            count.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + "生产的产品为：" + product.toString() + ",当前容器中还有产品个数为；" + count.intValue());
            //睡觉5s
            Thread.sleep(5000);
            //唤醒其他线程；
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
