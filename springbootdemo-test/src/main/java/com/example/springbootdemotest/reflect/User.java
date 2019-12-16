package com.example.springbootdemotest.reflect;

public class User {
    private String name;
    private String add;
    private String value = "123456";

    public User(String name, String add) {
        this.name = name;
        this.add = add;
    }

    private User(String name) {
        this.name = name;
    }
    public User(){

    }
    private void privateMethod(int i ) {
        System.out.println("这是一个私有方法:"+i);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void getMsg() {
        System.out.println(this.name + this.add);
    }

    public String getName() {
        return name;
    }


}
