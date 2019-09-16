package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo01;
    
    
    /**  
    * @Title: Product
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/5/20-15:50
    */
public class Product {
    private int id;
    public Product(int id ){
        this.id = id;
    }

        @Override
        public String toString() {
            return  "产品：" + this.id;
        }
    }
