package com.example.springbootdemofoundation;

import com.example.springbootdemofoundation.entity.AbstractEntity;
import com.example.springbootdemofoundation.entity.InterfaceEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoFoundationApplicationTests {

    @Test
    public void contextLoads() {
        int a = InterfaceEntity.a;
    }

}
