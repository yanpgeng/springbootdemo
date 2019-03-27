package com.example.springbootdemoentity.entity;


/**
 * @author YangPeng
 * @Title: Product
 * @ProjectName springboot
 * @Description: TODO
 * @date 2019/3/25-16:23
 */
public class Consumer {

    private String name;
    private int age;
    private String add;
    private String email;

    public Consumer() {
        this.name = "name";
        this.age = 12;
        this.add = "北京市历史互通";
        this.email = "6666.qq.com";
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", add='" + add + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
