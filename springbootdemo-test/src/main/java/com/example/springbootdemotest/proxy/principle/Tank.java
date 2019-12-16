package com.example.springbootdemotest.proxy.principle;

//实现接口的目标类
public class Tank implements Moveable
{
    @Override
    public void move() {
        System.out.println("Tank Move...");
    }
}
