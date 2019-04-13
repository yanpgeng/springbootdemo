package com.example.springbootdemoconsumer.controller;

import com.example.springbootdemoconsumer.service.UserService;
import com.example.springbootdemoentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser")
    public String getUser() {
        System.out.println("调用getUser方法");
        User user = userService.getUser();
        System.out.println(user.toString());
        return user.toString();
    }
}

