package com.example.springbootdemothread.dao;


import com.example.springbootdemoentity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
     User getUser();
     List<User> getPageUser();
}
