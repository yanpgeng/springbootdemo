package com.example.springbootdemoentity.config.repeatCommitConfig;


import com.example.springbootdemoentity.redisConfig.JedisClientCluster;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
    * @Title: AvoidRepeatableCommitAspect
    * @ProjectName springbootdemo
    * @Description: 对自定义注解进行切面编程，该注解为防止接口重复提交的注解
    * @author YangPeng
    * @company ccxcredit
    * @date 2019/4/8-11:44
    */

@Aspect
@Component
public class AvoidRepeatableCommitAspect {
    @Autowired
    private JedisClientCluster redisTemplate;
    /**
     * 实现逻辑：
     * 1、当客户端请求接口时，获取接口的ip地址、接口访问方法等信息，并将以上信息按照某种格式进行处理，得到一个字符串；
     * 2、以字符串为key，value为自动生成的字符，将上述信息保存到reids中，并保持3s（相关指令为setx），超过3s后该信息就被删除；
     * 3、当用户每次请求时都会生成key，并根据key查询是否存在value，如果存在，则表示该接口在3s内被同一个ip进行了多次请求，则返回相关信息，
     * 4、否则该请求能够正常执行，即某一用户3s内第一次请求该接口；
     */
    @Around("@annotation(com.example.springbootdemoentity.config.repeatCommitConfig.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request  = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        //这里ip写死，生产上实际使用的时候需要获取ip地址；
        String ip = "10.0.3.116";
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        String ipKey = String.format("%s#%s",className,name);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d",ip,hashCode+2);
        AvoidRepeatableCommit avoidRepeatableCommit =  method.getAnnotation(AvoidRepeatableCommit.class);
        //long timeout = avoidRepeatableCommit.timeout();
        String value =  redisTemplate.get(key);
        if (StringUtils.isNotBlank(value)){
            System.out.println("请勿重新提交");
            return "请勿重复提交";
        }
        redisTemplate.setx(key,3,UUID.randomUUID().toString());
        //执行方法
        Object object = point.proceed();
        return object;
    }
}
