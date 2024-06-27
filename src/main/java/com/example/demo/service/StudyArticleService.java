package com.example.demo.service;

import com.example.demo.pojo.StudyArticle;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public interface StudyArticleService {


    List<StudyArticle> getArticleByType(String type);
    String getArticleVideoUrl(String bucketName, String objectName);
}
