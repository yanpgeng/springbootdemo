package com.example.springbootdemoconsumer.dao;


import com.example.springbootdemoentity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
     User getUser();
}
