package com.example.springbootdemotest.proxy.staticPacakge;

import com.example.springbootdemotest.proxy.staticPacakge.ISinger;
import com.example.springbootdemotest.proxy.staticPacakge.Singer;
import com.example.springbootdemotest.proxy.staticPacakge.SingerProxy;

public class MainClass {
    public static void main(String[] args) {
        //创建一个真实角色
        Singer singer = new Singer();
        //创建一个代理角色，构造方法中需要真实角色；
        ISinger singerProxy = new SingerProxy(singer);
        //代理角色执行方法（代理角色内部调用真实角色对应的方法）
        singerProxy.sing();
    }
}
