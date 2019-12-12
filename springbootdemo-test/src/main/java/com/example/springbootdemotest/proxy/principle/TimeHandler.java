package com.example.springbootdemotest.proxy.principle;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler{

    private Object target;
    public TimeHandler(Object target){
        this.target = target;
    }
    @Override
    public void invoke(Object o, Method m) {
        long start = System.currentTimeMillis();
        System.out.println("StartTime:"+start);
        System.out.println(o.getClass().getName());
        try {
            m.invoke(target);
        }catch (Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("EndTime:"+end);
        System.out.println("time:"+(end-start));

    }
}
