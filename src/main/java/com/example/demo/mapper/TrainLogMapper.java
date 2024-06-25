package com.example.demo.mapper;

import com.example.demo.pojo.TrainLog;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainLogMapper {

    List<TrainLog> getAllTrainLog();
    void setTrainLog(TrainLog trainLog);
}
