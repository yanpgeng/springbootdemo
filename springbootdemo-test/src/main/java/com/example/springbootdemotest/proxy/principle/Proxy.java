package com.example.springbootdemotest.proxy.principle;

import org.apache.tomcat.websocket.WsRemoteEndpointImplClient;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

//代理对象，
public class Proxy {
    public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception {
        //1、定义方法字符串，用于保存方法信息；
        String methodStr = "";
        String rt = "\r\n";
        //2、接口方法获取；
        Method[] methods = infce.getMethods();
        for (Method m : methods) {
            //生成方法信息；
            methodStr += "@Override" + rt +
                    " public void "+ m.getName()+"(){"+rt+
                    "   try{" + rt +
                    "       Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt + //找到具体方法
                    "       h.invoke(this,md);" + rt +
                    "   }catch(Exception e){e.printStackTrace();}" + rt +
                    "}";
        }
        //3、生成实现类的对象信息
        String src ="package com.example.springbootdemotest.proxy.principle;"+ rt +
                "import java.lang.reflect.Method;" +rt +
                "public class $Proxy1 implements "+ infce.getName() +"{" +rt +
                "   public $Proxy1(InvocationHandler h){" +rt +
                "       this.h = h;" +rt +
                "   }" +rt +
                "   com.example.springbootdemotest.proxy.principle.InvocationHandler h;" +rt +
                    methodStr+
                "}";

        //4、动态生成$Proxy.java文件；
        //D:\software\gitRespository\springbootdemo\src\main\java\com\example\springbootdemotest\proxy\principle\$Proxy1.java
        String fileName = System.getProperty("user.dir")+"/springbootdemo-test/src/main/java/com/example/springbootdemotest/proxy/principle/$Proxy1.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();

        //5、执行动态编译过程；
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,null,null);
        Iterable units = fileMgr.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask t = compiler.getTask(null,fileMgr,null,null,null,units);
        t.call();
        fileMgr.close();

        //6、加载生成的$Proxy.class到内存中；
        //D:\software\gitRespository\springbootdemo\springbootdemo-test\src\main\java\com\example\springbootdemotest\proxy\clibPackage\MainClass.java
//        URL[] urls = new URL[]{new URL("file:"+System.getProperty("user.dir")+"/src/main/java")};
        URL[] urls = new URL[]{new URL("file:"+"D:/software/gitRespository/springbootdemo/springbootdemo-test/src/main/java")};

        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.example.springbootdemotest.proxy.principle.$Proxy1");
        System.out.println(c);
        Constructor ctr = c.getConstructor(InvocationHandler.class);
        Object m = ctr.newInstance(h);
        //m.move();
        return m;
    }

    public static void main(String[] args) throws Exception{
        Tank tank = new Tank();
        InvocationHandler h = new TimeHandler(tank);
        Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class,h);
        m.move();
    }
}
