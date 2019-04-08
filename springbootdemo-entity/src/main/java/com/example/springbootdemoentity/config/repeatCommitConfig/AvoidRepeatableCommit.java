package com.example.springbootdemoentity.config.repeatCommitConfig;


import java.lang.annotation.*;

/**
 * @author YangPeng
 * @Title: AvoidRepeatableCommit
 * @ProjectName springbootdemo
 * @Description: 编写一个自定义注解
 * @company ccxcredit
 * @date 2019/4/8-11:43
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidRepeatableCommit {
    long timeout() default 3000;
}
