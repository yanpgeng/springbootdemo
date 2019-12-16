package com.example.springbootdemotest.proxy.clibPackage;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {
    private  Object target;
    public ProxyFactory(Object target){
        this.target = target;
    }
    public Object getProxyInstance(){
        //1.工具类
         Enhancer en = new Enhancer();
         //2.设置父类
         en.setSuperclass(target.getClass());
         //3.设置回调函数
         en.setCallback(this);
         //4.创建子类(代理对象)
         return en.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //此处在真实对象方法执行前编写自己的业务逻辑
        System.out.println("CGLIB动态代理————向观众问好");
        Object returnValue = method.invoke(target,objects);//真实对象对应方法的调用；
        //此处在真实对象方法执行前编写自己的业务逻辑
        System.out.println("CGLIB动态代理————谢谢大家");
        return returnValue;
    }
}
