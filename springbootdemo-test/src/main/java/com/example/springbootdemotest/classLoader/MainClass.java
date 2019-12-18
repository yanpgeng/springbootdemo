package com.example.springbootdemotest.classLoader;

import org.aspectj.util.LangUtil;

import java.util.Arrays;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
//        PersionClass persionClass = new PersionClass();
//        Class<?> clazz =  PersionClass.class;
//        persionClass.getClass();

        bootClassLoaderLoadingPath();
        extClassLoaderLoadingPath();
        appClassLoaderLoadingPath();
//        getProcess();

    }
    //Bootstrap类加载器加载的jar包
    public static  void bootClassLoaderLoadingPath(){
        String bootStrapLoadingPath = System.getProperty("sun.boot.class.path");
        List<String> list = Arrays.asList(bootStrapLoadingPath.split(";"));
        System.out.println("启动类的类加载器加载jar包路径如下");
        //启动类的类加载器中会有一个“C:\Program Files\Java\jdk1.8.0_211\jre\classes”路径，
        //这个路径表示你自定义的class文件放到上述路径中就可以被启动类加载器加载；
        list.forEach(s ->System.out.println(s));
    }
    //扩展类加载器加载的jar包
    public static  void extClassLoaderLoadingPath(){
        System.out.println("-----------------");
        System.out.println("扩展类类加载器加载jar包路径如下");
        String bootStrapLoadingPath = System.getProperty("java.ext.dirs");
        List<String> list = Arrays.asList(bootStrapLoadingPath.split(";"));
        list.forEach(s ->System.out.println(s));
    }
    //应用类加载器加载的jar包
    public static  void appClassLoaderLoadingPath(){
        System.out.println("-----------------");
        System.out.println("应用类类加载器加载jar包路径如下：");
        String bootStrapLoadingPath = System.getProperty("java.class.path");
        List<String> list = Arrays.asList(bootStrapLoadingPath.split(";"));
        list.forEach(s ->System.out.println(s));
    }


    //代码验证双亲委派模型加载过程
    public static void getProcess(){
        System.out.println(MainClass.class.getClassLoader());
        System.out.println(MainClass.class.getClassLoader().getParent());
        System.out.println(MainClass.class.getClassLoader().getParent().getParent());

    }
}
