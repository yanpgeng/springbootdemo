package com.example.springbootdemotest.proxy.jdkPackage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainClass {
    public static void main(String[] args) {
        //1、创建一个真实角色
        Singer target = new Singer();
        //2、调用Proxy.newProxyInstance方法，并构造一个InvocationHandler对象，
        //3、在对象内部重写invoke方法，同时调用method.invoke(target,args);
        //4、并在该方法的上下添加自己的代码逻辑
        //其中：target.getClass().getClassLoader()：获取类加载器，用来生成代理对象；
        //	    target.getClass().getInterfaces()获取接口元信息；
        ISinger iSinger = (ISinger) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理---向观众问好");//在真实对象的方法被调用“前”编写自己的业务逻辑
                Object returnValue = method.invoke(target,args);//此处通过反射调用真实对象对应的方法；
                System.out.println("动态代理---向观众问好");//在真实对象的方法被调用“后”编写自己的业务逻辑
                return returnValue;
            }
        });
        iSinger.sing();
    }
}
