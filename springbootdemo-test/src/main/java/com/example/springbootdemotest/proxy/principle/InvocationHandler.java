package com.example.springbootdemotest.proxy.principle;

import java.lang.reflect.Method;

//实现接口的目标类；
public interface InvocationHandler {
     void invoke(Object o, Method m);
}
