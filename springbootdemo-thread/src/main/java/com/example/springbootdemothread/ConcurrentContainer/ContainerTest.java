package com.example.springbootdemothread.ConcurrentContainer;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author YangPeng
 * @Title: ContainerTest
 * @ProjectName springbootdemo
 * @Description: TODO
 * @company ccxcredit
 * @date 2019/6/11-14:43
 */
public class ContainerTest {
    static Vector<String> stringVector = new Vector<>();

    public static void main(String[] args) {
        stringVector.add("111");
        stringVector.add("222");
        stringVector.add("333");
        stringVector.add("444");
        Thread thread1 = new Thread(() -> {
            stringVector.forEach((x) -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //使用vector时，当遍历vector容器的同时，又其他线程向该容器中添加或者删除对象时，该遍历进程会抛出异常（ConcurrentModificationException)
                stringVector.remove(x);
                System.out.println("值为：" + x+";当前vector中还有元素个数："+stringVector.size());
            });
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("添加的值为：555");
            stringVector.addElement("555");
        });
        thread1.start();
        thread2.start();


//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(2);
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Integer integer = iterator.next();
//            if (integer == 2)
//                list.remove(integer);
//        }
    }
}
