package com.example.springbootdemothread.controller;

import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemothread.dao.UserMapper;
import com.example.springbootdemothread.entity.ThreadEntity2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;


/**
 * 证评需求：
 *  定义一个接口，该接口可并发访问，并发访问数量不高，1s在50以内；且通过该接口计算现金流，并将现金流结果到处到excel中，
 *  并共浏览器进行下载；
 * 实现方案：
 * 1、使用线程池进行控制并发数量：将超过并发数量的线程保存到blockqueue中，超过部分直接报错（线程池的rejectException)；
 * 2、使用CountDownLatch做“串行”处理，确定线程池中的当前请求是否执行结束，如果执行结束，则调用文件导出方法进行下载；
 * 3、使用runnable进行算法的计算和文件的保存操作；
 *      1)、定义Runnable对象，参数有：ID、countDownLatch、业务参数、dao层对象（用于查询数据库）、
 *          ConcurrentHashMap（用于保存计算过程中的异常、正确结果等信息）；
 *      2）、重写run方法，在该方法中执行的操作有:
 *          I、使用dao层对象，通过ID获取入参想项；
 *          II、对入参项进行校验，校验通过，则通过模型计算现金流，如果发生异常，则保存到ConcurrentHashMap对象中，并保存相关异常信息，打印到log中；
 *          III、当算法计算结束，将计算结果通过代码写入到excel中并保存到服务器某一路径下；
 *          IV、使用countDownLatch.await()代码进行“同步串行”操作，并根据ConcurrentHashMap对象中的数据判断当前模型是否正常执行结束，如果是，
 *              则调用下载方法，让浏览器可以下载计算结果；
 *      3）、通过构造函数创建对象，并使用线程池中的线程执行该任务；
 *
 *
 * 线程池相关变量：
 *  1、线程池核心线程大小：CPU个数；
 *  2、线程池最大线程大小：CPU个数；
 *  3、等待队列大小:2倍的CPU个数；
 */
@RestController
public class ThreadPoolController {
    //定义一个map，用来保存计算结果
    static ExecutorService executorService = new ThreadPoolExecutor(2, 5,
            0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/threadTestDemo")
    public String threadTestDemo(HttpServletResponse response, int i) {
        ConcurrentHashMap<String, User> con = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            executorService.submit(new ThreadEntity2(i, userMapper, con, countDownLatch));
            countDownLatch.await();
            System.out.println(con.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            downloadLocal(response, i + "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "计算成功！";
    }

    public void downloadLocal(HttpServletResponse response, String i) throws FileNotFoundException {
        // 下载本地文件
        String fileName = i + ".txt"; // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("E:/file/file/" + i + ".txt");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
