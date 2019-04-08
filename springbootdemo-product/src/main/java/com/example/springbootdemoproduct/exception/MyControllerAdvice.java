package com.example.springbootdemoproduct.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
    * @Title: MyControllerAdvice
    * @ProjectName springbootdemo
    * @Description: TODO
    * @author YangPeng
    * @date 2019/3/28-17:45
    */
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = MyException.class)
    public ModelAndView errorHandler(MyException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }

}
