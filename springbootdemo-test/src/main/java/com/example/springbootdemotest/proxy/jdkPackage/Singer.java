package com.example.springbootdemotest.proxy.jdkPackage;

public class Singer implements ISinger{

    @Override
    public void sing() {
        System.out.println("sing a song");
    }
}
