package com.example.springbootdemoconsumer.service;

import com.example.springbootdemoconsumer.dao.UserMapper;
import com.example.springbootdemoentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(){
        return userMapper.getUser();
    }
}
