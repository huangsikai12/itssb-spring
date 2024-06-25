package com.example.demo.service;

import com.example.demo.mapper.TrainLogMapper;
import com.example.demo.pojo.TrainLog;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainningLogServiceImpl implements TrainningLogService{

    @Autowired
    private MinioClient minioClient;
    @Resource
    private TrainLogMapper trainLogMapper;

    @Override
    public InputStream getFileLog(String bucketName, String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
        } catch (MinioException e) {
            throw new RuntimeException("Error occurred while fetching the file from Minio", e);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> listObjectNames(String bucketName) {
        List<String> objectNames = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
            for (Result<Item> result : results) {
                Item item = result.get();
                objectNames.add(item.objectName());
            }
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error occurred while listing objects from Minio", e);
        }
        return objectNames;
    }

    @Override
    public String uploadFileLog(String bucketName,MultipartFile file) {
        try {
            String objectName = file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            return "File uploaded successfully: " + objectName;

        } catch (MinioException | IOException e) {
            throw new RuntimeException("Error occurred while uploading file to Minio", e);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";


    }

    @Override
    public List<TrainLog> getAllTrainLog() {
        return trainLogMapper.getAllTrainLog();
    }

    @Override
    public void setTrainLog(TrainLog trainLog) {
        trainLogMapper.setTrainLog(trainLog);
    }

}
