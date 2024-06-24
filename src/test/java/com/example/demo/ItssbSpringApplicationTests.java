package com.example.demo;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ItssbSpringApplicationTests {

    @Resource
    UserService userService;

    @Test
    void contextLoads() {
        User userById = userService.getUserById(1);
        System.out.println(userById.getName());
    }

}
