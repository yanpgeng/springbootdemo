package com.example.springbootdemotest.syntax;


import com.example.springbootdemotest.interfaces.*;

/**
 * @author YangPeng
 * @Title: Syntax1
 * @ProjectName springbootdemo
 * @Description: lambda 表达式的基础语法；
 * @company ccxcredit
 * @date 2019/5/24-15:34
 */
public class Syntax1 {
    public static void main(String[] args) {
        //1、lambda表达式的基础语法
        //lambda是一个匿名函数
        //返回值类型(pass)、方法名（pass)、参数列表(重点）、方法体（重点）；
        // ()：用来表示参数列表；
        //{}:用来描述方法体
        // ->:使用lambda运算符（读作：goes to）
        //1、无参无返回：
        LambdaNonReturnNonParameter lambda1 = () -> {
            System.out.println("无参数无返回值的lambda表达式");
        };
        lambda1.test();
        //2、有参数无返回值的
        LambdaNonReturnSingleParameter lambda2 = (int a) -> {
            System.out.println("有参数，无返回值的lambda表达式：" + a);
        };
        lambda2.test(3);
        //3、多参数无返回值的lambda表达式
        LambdaNonReturnMutipleParameter lambda3 = (int a, int b) -> {
            System.out.println("多参数无返回值的lambda表达式：" + a + b);
        };
        lambda3.test(3, 5);
        //4、无参数有返回值的lambda表达式
        LambdaSingleReturnNoneParameter lambda4 = () -> {
            System.out.println("无参数有返回值的lambda表达式：100");
            return 100;
        };
        lambda4.test();
        //5、单一参数单一返回值的lambda表达式：
        LambdaSingleRetrunSingleParameter lambda5 = (int a) -> {
            System.out.println("单一参数单一返回值的lambda表达式：" + (100 + a));
            return a + 100;
        };
        lambda5.test(100);
        //6、多参数单一返回值的lambda表达式：
        LambdaSingleReturnMutiplyParameter lambda6 = (int a, int b) -> {
            System.out.println("多参数单一返回值的lambda表达式：" + (a + b));
            return a + b;
        };
        lambda6.test(100, 200);
    }
}
