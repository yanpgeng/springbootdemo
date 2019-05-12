package com.example.springbootdemothread.entity;

import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemothread.dao.UserMapper;
import com.example.springbootdemothread.service.UserService;
import com.example.springbootdemothread.utils.WriteUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ThreadEntity2 implements Runnable{
    private UserService userService;
    private UserMapper userMapper;
    private ConcurrentHashMap<String,User> concurrentHashMap;
    private int  count ;
    private CountDownLatch countDownLatch;

//    //构造方法
    public ThreadEntity2(int count, UserService userService,
                         ConcurrentHashMap<String,User> concurrentHashMap,CountDownLatch countDownLatch) {
        this.count = count;
        this.userService = userService;
        this.concurrentHashMap = concurrentHashMap;
        this.countDownLatch = countDownLatch;
    }
    public ThreadEntity2(int count, UserMapper userMapper,
                         ConcurrentHashMap<String,User> concurrentHashMap,CountDownLatch countDownLatch) {
        this.count = count;
        this.userMapper = userMapper;
        this.concurrentHashMap = concurrentHashMap;
        this.countDownLatch = countDownLatch;
    }
    //构造方法
//    public ThreadEntity2(int count,ConcurrentHashMap<String,User> concurrentHashMap,
//                         CountDownLatch countDownLatch) {
//        this.count = count;
//        this.countDownLatch = countDownLatch;
//        this.concurrentHashMap = concurrentHashMap;
//    }

    @Override
    public void run() {
        try {
            //Thread.currentThread().setName("第"+count+"个计算子线程");
            Thread.sleep(count*3000);
            System.out.println("当前线程名称为："+Thread.currentThread().getName()+";睡眠时间："+count*3);
            System.out.println(userMapper.hashCode());
            User user = userMapper.getUser();
            concurrentHashMap.put(count+"",user);
            WriteUtils.writeFiles(count);
         //   System.out.println(Thread.currentThread().getName()+"即将结束并调用countDown方法");
            countDownLatch.countDown();
            //  System.out.println(user.hashCode());
            //System.out.println(concurrentHashMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
