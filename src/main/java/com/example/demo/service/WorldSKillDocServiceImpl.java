package com.example.demo.service;

import com.example.demo.mapper.WorldSkillDocMapper;
import com.example.demo.pojo.TrainLog;
import com.example.demo.pojo.WorldSkillDoc;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class WorldSKillDocServiceImpl implements WorldSKillDocService {

    @Autowired
    WorldSkillDocMapper worldSkillDocMapper;
    @Autowired
    private MinioClient minioClient;

    @Override
    public InputStream getDoc(String bucketName, String objectName) {
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

    public List<WorldSkillDoc> getAllDoc()
    {
        List<WorldSkillDoc> worldSkillDocList = worldSkillDocMapper.getAllDoc();

        Iterator<WorldSkillDoc> iterator = worldSkillDocList.iterator();
        while (iterator.hasNext()) {
            WorldSkillDoc doc = iterator.next();
            int parent_id = doc.getIs_parent();
            if (parent_id!=0)
            {
                for (WorldSkillDoc worldSkillDoc : worldSkillDocList) {
                    if (worldSkillDoc.getId() == parent_id) {
                        if (worldSkillDoc.getChildren() == null) {
                            List<WorldSkillDoc> worldSkillDocs = new ArrayList<>();
                            worldSkillDocs.add(doc);
                            worldSkillDoc.setChildren(worldSkillDocs);
                            continue;
                        }
                        worldSkillDoc.getChildren().add(doc);
                    }
                }
                iterator.remove();
            }
        }

        return worldSkillDocList;
    }

    @Override
    public String uploadDoc(String bucketName, MultipartFile file,String file_name) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file_name)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            return "文件上传成功：" + file_name;

        } catch (MinioException | IOException e) {
            throw new RuntimeException("Error occurred while uploading file to Minio", e);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void setDoc(WorldSkillDoc doc) {
        worldSkillDocMapper.setDoc(doc);

    }


}
