package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo01;


/**
 * @author YangPeng
 * @Title: Consumer
 * @ProjectName springbootdemo
 * @Description: 消费者线程类；
 * @company ccxcredit
 * @date 2019/5/20-15:49
 */
public class Consumer implements Runnable {
    private String name;
    private Storage s = null;

    public Consumer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            while (true){
                Product product = s.pop();
                System.out.println(name + "已消费(" + product.toString() + ").容器中现由产品个数为："+s.size());
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
