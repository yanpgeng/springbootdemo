package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo01;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
    * @Title: Storage
    * @ProjectName springbootdemo
    * @Description: 生产者消费者线程容器类
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/5/20-15:49
    */
public class Storage {
    /**
     * 定义一个容器，该容器使用BlockingQueue，该容器为线程安全的消息队列；
     * 且使用linkedBlockingQueue，该容器默认大小为Integer.MAX;所以在初始化的时候不需要指定大小；
     * ArrayBlockingQueue,在初始化的时候，不许传入一个容量大小的值(capacity:容量)；
     */
//    BlockingQueue<Product> linkedBlockingQueue = new LinkedBlockingQueue<>();
    BlockingQueue<Product>  arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    /**
     * 生产者生产产品
     */
    public void push(Product p) throws InterruptedException {
        arrayBlockingQueue.put(p);
    }
    public Product pop() throws InterruptedException {
        //使用队列的take()方法，即如果该队列为空，则会处于阻塞状态；
        //如果使用poll()方法，即如果该队列为空，则返回null，就无法达到阻塞效果，生产者消费者模型无效；
       Product product =  arrayBlockingQueue.take();
       return product;
    }
    public int size(){
        return arrayBlockingQueue.size();
    }

}
