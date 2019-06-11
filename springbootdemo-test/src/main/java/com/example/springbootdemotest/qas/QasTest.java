package com.example.springbootdemotest.qas;


import com.example.springbootdemotest.interfaces.LambdaSingleReturnMutiplyParameter;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
    * @Title: QasTest
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/6/5-16:58
    */
public class QasTest {
//    AbstractQueuedSynchronizer
//    ReentrantLock
public static void main(String[] args) {
    LambdaSingleReturnMutiplyParameter lambda1 =  (a,b)->{
        return a+b;
    };
    System.out.println(lambda1.test(1,2));
}
}
