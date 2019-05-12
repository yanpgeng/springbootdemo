package com.example.springbootdemothread.entity;


import com.example.springbootdemothread.utils.WriteUtils;

import java.io.IOException;

public class ThreadEntity implements Runnable{
    private int  count ;

    //构造方法
    public ThreadEntity(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
            WriteUtils.writeFiles(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
