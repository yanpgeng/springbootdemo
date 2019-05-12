package com.example.springbootdemothread.service;

import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemothread.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserService implements Serializable {
    @Autowired
    private UserMapper userMapper;

    public User getUser(){
        return userMapper.getUser();
    }
    public List<User> getPageUser(){
        return userMapper.getPageUser();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
