package com.example.demo.service;

import com.example.demo.pojo.TrainLog;
import com.example.demo.pojo.WorldSkillDoc;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface WorldSKillDocService {


    InputStream getDoc(String bucketName, String objectName);
    List<WorldSkillDoc> getAllDoc();
    String uploadDoc(String bucketName,MultipartFile file,String file_name);
    void setDoc(WorldSkillDoc worldSkillDoc);


}
