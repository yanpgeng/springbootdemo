package com.example.springbootdemotest.proxy.principle;

public class Tank implements Moveable
{
    @Override
    public void move() {
        System.out.println("Tank Move...");
    }
}
