package com.example.springbootdemothread.controller;

import com.example.springbootdemothread.utils.HttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class HttpReqController {
    ExecutorService executorService = new ThreadPoolExecutor(10,
            10, 0, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10));
    @RequestMapping(value = "test")
    public void test() {
        int num =4;
        for (int i = 0; i < num; i++) {
            executorService.submit(new ReqEntity(i));
        }
    }

    class ReqEntity implements Runnable{
        private int i ;

        public ReqEntity(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            HttpUtils.doGet("http://localhost:7003/thread/threadTestDemo?i="+i);
            //System.out.println("开启第"+i+"个线程");
        }
    }

    public static void main(String[] args) {
        double a = 50.0;
        if(a>=100){
            System.out.println(a);
        }else{
            System.out.println("j; a ");
        }
    }
}
