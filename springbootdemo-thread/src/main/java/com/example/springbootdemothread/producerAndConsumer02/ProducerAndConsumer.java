package com.example.springbootdemothread.producerAndConsumer02;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static rx.schedulers.Schedulers.start;

/**
 * 面试题：
 * 写一个固定容量同步容器，拥有put和get方法，以即getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用；
 * 使用wait和nofity/notifyAll来实现；
 */
public class ProducerAndConsumer<T> {
    final private LinkedList<T> linkedList = new LinkedList<T>();
    //容器中做多10个元素；
    final private int MAX = 10;
    //容器中元素的个数；
    private AtomicInteger count = new AtomicInteger(0);


    synchronized void put(T t) {
        try {
            while (linkedList.size() == MAX) {
                System.out.println("当前容器个数为："+size());
                System.out.println("生产者线程:"+Thread.currentThread().getName()+"即将释放锁，并处于wait状态！");
                this.wait();
            }
            linkedList.add(t);
            count.getAndIncrement();
            System.out.println("生产者:"+Thread.currentThread().getName()+"往容器添加一个,当前容器中的个数为："+size());
            System.out.println("生产者通知消费者进程，容器中已经有东西了，可以进行消费了。。");
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized T get() {
        T t = null;
        try {
            while (linkedList.size() == 0) {
                System.out.println("当前容器个数为："+size());
                System.out.println("消费者线程:"+Thread.currentThread().getName()+"即将释放锁，并处于wait状态！");
                this.wait();
            }
            t = linkedList.removeFirst();
            count.getAndDecrement();
            System.out.println("消费者线程："+Thread.currentThread().getName()+"从容器中获取一个，当前容器中的个数为:"+size());
            System.out.println("消费者通知生产者，已经被消费了一个，可以进行生产了。");
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return t;
    }

    Integer size() {
        return count.intValue();
    }

    public static void main(String[] args) {
        ProducerAndConsumer<String> producerAndConsumer = new ProducerAndConsumer<>();
        System.out.println(producerAndConsumer.size());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++)
                    System.out.println(producerAndConsumer.get());
            }, "c" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++)
                    producerAndConsumer.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}

