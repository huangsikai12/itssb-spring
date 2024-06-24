package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    User getUserById(Integer id);
    User getUserByInfo(User user);
}
