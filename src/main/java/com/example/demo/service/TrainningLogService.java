package com.example.demo.service;

import com.example.demo.pojo.TrainLog;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface TrainningLogService {

    InputStream getFileLog(String bucketName, String objectName);
    List<String> listObjectNames(String bucketName);
    String uploadFileLog(String bucketName,MultipartFile file);
    List<TrainLog> getAllTrainLog();
    void setTrainLog(TrainLog trainLog);

}
