package com.example.springbootdemotest.proxy.principle;

import java.lang.reflect.Method;

//定义一个方法调用接口
public interface InvocationHandler {
     void invoke(Object o, Method m);
}
