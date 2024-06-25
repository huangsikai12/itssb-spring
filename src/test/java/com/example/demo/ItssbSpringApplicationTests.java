package com.example.demo;

import com.example.demo.pojo.TrainLog;
import com.example.demo.pojo.User;
import com.example.demo.service.TrainningLogService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ItssbSpringApplicationTests {

    @Resource
    UserService userService;
    @Autowired
    TrainningLogService trainningLogService;


    @Test
    void contextLoads() {
        User userById = userService.getUserById(1);
        System.out.println(userById.getName());
    }

    @Test
    void contextLoads2() {
        System.out.println(new Result<>(200,"获取成功",trainningLogService.listObjectNames("trainninglog")));
    }

    @Test
    void contextLoads3() {
        TrainLog trainLog= new TrainLog();
        trainLog.setFile_name("111");
        trainLog.setContent("222");
        trainLog.setUser_name("333");
        trainLog.setDate(new Date());
        trainningLogService.setTrainLog(trainLog);
    }

}
