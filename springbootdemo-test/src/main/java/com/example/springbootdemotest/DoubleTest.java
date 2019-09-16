package com.example.springbootdemotest;


import org.springframework.util.StopWatch;

import java.math.BigDecimal;

/**
    * @Title: DoubleTest
    * @ProjectName springbootdemo
    * @Description: Double类型数据保留四位小数多种方法效率比较
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/7/22-11:21
    */
public class DoubleTest {

        public static void main(String[] args) throws Exception {
            method1();
            Thread.sleep(100);
            method2();
            Thread.sleep(100);
            method3();
        }


        private static void  method1(){
            double value =0.12345566;
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            for(int i =0;i<100000000;i++){
                value = (double) Math.round(value * 10000) / 10000;
            }
            stopWatch.stop();
            System.out.println("使用Math.round方法，先转成int，再处以10000，10亿次用时为:"+stopWatch.toString());
        }

        private static void  method2(){
            double value =0.12345566;
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            for(int i =0;i<1000000;i++){
                 BigDecimal b = new BigDecimal(value);
                value = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            stopWatch.stop();
            System.out.println("使用Bigdecimal进行转换，10亿次用时为:"+stopWatch.toString());
        }
        private static void  method3(){
            double value =0.12345566;
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            for(int i =0;i<10000000;i++){
                String stringValue=String.format("%.2f", value);
            }
            stopWatch.stop();
            System.out.println("使用String进行转换，10亿次用时为:"+stopWatch.toString());
        }
}
