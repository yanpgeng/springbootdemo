package com.example.springbootdemotest.reflect;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.FileDescriptor;
import java.lang.reflect.*;

public class MainClass {
    public static void main(String[] args) {
        getPrivateConstructor();
        getObject();
        getPrivateFiled();
        getPrivateMethod();
    }

    //通过反射方式生成私有的构造方法；
    public static void getPrivateConstructor() {
        try {
            Class<?> userClass = null;
            userClass = Class.forName("com.example.springbootdemotest.reflect.User");
            Constructor<?> constructor = userClass.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            User user = (User) constructor.newInstance("yangpeng");
            System.out.println("反射生成私有构造方法----------对象地址为：" + user.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过反射生成对象：
    public static void getObject() {
        try {
            Class<?> classObject = Class.forName("com.example.springbootdemotest.reflect.User");
            User user = (User) classObject.newInstance();
            user.setAdd("beijing");
            user.setName("yangpeng");
            System.out.print("反射生成对象--------对象信息为：");
            user.getMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //反射私有属性
    public static void getPrivateFiled() {

        try {
            Class<?> classObject = Class.forName("com.example.springbootdemotest.reflect.User");
            Object classUser = classObject.newInstance();
            Field field = classObject.getDeclaredField("value");
            field.setAccessible(true);
            String value = (String) field.get(classUser);
            System.out.println("获取私有属性成功：value：" + value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //反射私有方法
    public static void getPrivateMethod() {
        try {
            Class<?> classObject = Class.forName("com.example.springbootdemotest.reflect.User");
            Object classUser = classObject.newInstance();
            Method method = classObject.getDeclaredMethod("privateMethod",int.class);
            method.setAccessible(true);
            method.invoke(classUser, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
