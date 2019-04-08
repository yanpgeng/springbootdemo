package com.example.springbootdemoentity.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author YangPeng
 * @Title: Product
 * @ProjectName springboot
 * @Description: 实体类使用@Data注解（该注解对应依赖为lombok，使用该注解可以省略getset方法、toString方法、构造方法等方法的书写
 * 使代码看起来更加整洁美观；
 * @date 2019/3/25-16:23
 */
@Data
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

   /* @Override
    public String toString() {
        return "Consumer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", add='" + add + '\'' +
                ", email='" + email + '\'' +
                '}';
    }*/
}
