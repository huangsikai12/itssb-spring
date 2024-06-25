package com.example.demo.controller;


import com.example.demo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public Result<User> Login(@RequestBody User user)
    {
        User u = userService.getUserByInfo(user);
        if (u!=null)
        {
            return new Result<User>(200,"登陆成功",new User(u.getId(),u.getName(),""));

        }
        return new Result<User>(404,"登陆失败",null);
    }
}
