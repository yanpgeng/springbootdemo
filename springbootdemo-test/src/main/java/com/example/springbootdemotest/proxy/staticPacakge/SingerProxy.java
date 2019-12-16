package com.example.springbootdemotest.proxy.staticPacakge;

public class SingerProxy implements ISinger {
    private ISinger iSinger ;
    public SingerProxy(ISinger iSinger){
        this.iSinger = iSinger;
    }

    @Override
    public void sing() {
        System.out.println("向观众问好");
        iSinger.sing();
        System.out.println("谢谢大家");
    }
}
