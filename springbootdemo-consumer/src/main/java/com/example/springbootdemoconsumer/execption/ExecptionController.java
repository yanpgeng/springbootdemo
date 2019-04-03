package com.example.springbootdemoconsumer.execption;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ExecptionController
 * @ProjectName springbootdemo
 * @Description: TODO
 * @author YangPeng
 * @date 2019/3/27-17:30
 */

/**
 * @ControllerAdvice(basePackages = "com.example") 注解不添加basePackages 表示加了@Controller和@RestController都能控制
 */
@ControllerAdvice
public class ExecptionController {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> excptionhandler(Exception ex) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("errorCode", "500");
        map.put("errorMsg", "系统内部错误");
        map.put("errorInfo", ex.getMessage());

        return map;
    }
}
