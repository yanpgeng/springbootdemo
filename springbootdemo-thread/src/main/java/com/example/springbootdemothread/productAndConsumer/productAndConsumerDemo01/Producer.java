package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo01;


import java.util.Random;

/**
 * @author YangPeng
 * @Title: Producer
 * @ProjectName springbootdemo
 * @Description: 生产者线程类；
 * @company ccxcredit
 * @date 2019/5/20-16:13
 */
public class Producer implements Runnable {
    /**
     * 定义生产者的姓名，生产的产品的存放路径；
     */
    private String name;
    private Storage s = null;

    /**
     * 构造方法；
     */

    public Producer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            //线程一直往容器中生产产品；
            while (true) {
                Product product = new Product(new Random().nextInt(1000));
                s.push(product);
                System.out.println(name + "已生产(" + product.toString() + ").容器中现有产品个数为："+s.size());
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
