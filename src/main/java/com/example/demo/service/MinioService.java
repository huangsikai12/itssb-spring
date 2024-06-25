package com.example.demo.service;

import java.io.InputStream;

public interface MinioService {

    InputStream getFile(String bucketName, String objectName);
}
