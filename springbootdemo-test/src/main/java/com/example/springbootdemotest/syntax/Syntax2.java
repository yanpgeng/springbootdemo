package com.example.springbootdemotest.syntax;


import com.example.springbootdemotest.interfaces.LambdaNonReturnMutipleParameter;
import com.example.springbootdemotest.interfaces.LambdaNonReturnSingleParameter;
import com.example.springbootdemotest.interfaces.LambdaSingleReturnMutiplyParameter;
import com.example.springbootdemotest.interfaces.LambdaSingleReturnNoneParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author YangPeng
 * @Title: Syntax2
 * @ProjectName springbootdemo
 * @Description: TODO
 * @company ccxcredit
 * @date 2019/5/24-15:57
 */
public class Syntax2 {
    public static void main(String[] args) {
        //1、lambda可以精简参数的类型（因为接口中已经定义了参数的类型）
        LambdaNonReturnMutipleParameter lambda1 = (a, b) -> {
            System.out.println(a + b);
        };

        //2、lambda可以精简小括号；
        LambdaNonReturnSingleParameter lambda2 = x -> {
            System.out.println("hello world");
        };
        //3、关于方法、大括号的精简；
        //如果方法体中只有一句代码时，大括号是可以省略的；
        LambdaNonReturnSingleParameter lambda3 = x -> System.out.println("hello world");

        //4、对方法和大括号精简的补充
        //如果方法体中唯一的一条语句是返回语句,则在省略大括号的同时，也必须省略return；
        LambdaSingleReturnNoneParameter lambda4 = () -> 10;

        LambdaSingleReturnMutiplyParameter lambda5 = (a,b)->a+b;


        //lambda表达式的方法引用：可以快速的将一个lambda表达式的实现直线给一个已经实现的方法；
        //语法：方法的隶属这::方法名
        //注意：1、参数数量和类型一定要和接口中定义的方法一致；2、返回值类型也必须同接口中定义的返回值类型一致；

        List list = new ArrayList();
        Collections.addAll(list,"1","2","3","4","5","6");
        System.out.println(list);
    }

}
