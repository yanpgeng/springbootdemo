package com.example.springbootdemothread.controller;

import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemothread.entity.ThreadEntity2;
import com.example.springbootdemothread.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser")
    public String getUser() {
        System.out.println("调用getUser方法");
        User user = userService.getUser();
        System.out.println(user.toString());
        return user.toString();
    }

    @RequestMapping(value = "/getPageUser")
    public PageInfo<User> getPageUser(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        System.out.println("调用getUser方法");
        List<User> userList = userService.getPageUser();
        System.out.println(userList.toString());
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
        //return user.toString();
    }

    //获取服务器CPU个数；
    static final int nThreads = Runtime.getRuntime().availableProcessors();

    //第三个参数:当线程数量大于核心线程池数量，且blockqueue已经满的时候，需要创建新的线程达到线程最大值，那么当新的
    //线程执行队列中的任务结束之后，新的线程的等待时间；
    //核心线程池数量为2，当 当前线程数量大约2时，将线程保存到线程队列中，且线程队列中最多能保存10个线程；
    //当线程队列也满了的时候，需要启动新的线程，且新的线程+原线程的数量不能大于5；
    static ExecutorService executorService = new ThreadPoolExecutor(2, 2,
            0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));
    AtomicInteger integer = new AtomicInteger(0);

    @RequestMapping(value = "/threadTest")
    public String threadTest(int i ) {
        try {
           Thread.currentThread().setName("第"+integer+"个controller线程");
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ConcurrentHashMap<String, User> concurrentHashMap = new ConcurrentHashMap<>();
//            for (int i = 0; i < 100; i++) {
//            System.out.println(userService.hashCode());
//            executorService.submit(new ThreadEntity(i));
                executorService.submit(new ThreadEntity2(i,userService, concurrentHashMap, countDownLatch));
//            }
            String a = "";
            String b = "";
            a.equals(b);
            countDownLatch.await(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}

