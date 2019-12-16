package com.example.springbootdemotest.proxy.principle;
import java.lang.reflect.Method;
public class $Proxy1 implements com.example.springbootdemotest.proxy.principle.Moveable{
   public $Proxy1(InvocationHandler h){
       this.h = h;
   }
   com.example.springbootdemotest.proxy.principle.InvocationHandler h;
@Override
 public void move(){
   try{
       Method md = com.example.springbootdemotest.proxy.principle.Moveable.class.getMethod("move");
       h.invoke(this,md);
   }catch(Exception e){e.printStackTrace();}
}}