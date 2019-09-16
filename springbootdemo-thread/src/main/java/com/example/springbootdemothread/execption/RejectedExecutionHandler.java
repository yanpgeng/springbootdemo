package com.example.springbootdemothread.execption;


import java.util.concurrent.ThreadPoolExecutor;

/**
    * @Title: RejectedExecutionHandler
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/7/29-10:28
    */
public class RejectedExecutionHandler implements java.util.concurrent.RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//            executor.setRejectedExecutionHandler();
    }
}
