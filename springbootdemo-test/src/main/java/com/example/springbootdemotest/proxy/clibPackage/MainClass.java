package com.example.springbootdemotest.proxy.clibPackage;

public class MainClass {
    public static void main(String[] args) {
        Singer singer = new Singer();
        Singer proxySinger = (Singer) new ProxyFactory(singer).getProxyInstance();
        proxySinger.sing();
    }

}
