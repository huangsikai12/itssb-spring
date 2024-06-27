package com.example.demo.service;

import com.example.demo.mapper.StudyArticleMapper;
import com.example.demo.pojo.StudyArticle;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StudyArticleServiceImpl implements StudyArticleService {

    @Autowired
    private StudyArticleMapper studyArticleMapper;
    @Autowired
    private MinioClient minioClient;

    @Override
    public List<StudyArticle> getArticleByType(String type) {
        return studyArticleMapper.getArticleByType(type);
    }

    @Override
    public String getArticleVideoUrl(String bucketName, String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(7, TimeUnit.DAYS)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
