package com.example.demo.controller;


import com.example.demo.Result;
import com.example.demo.pojo.TrainLog;
import com.example.demo.pojo.WorldSkillDoc;
import com.example.demo.service.TrainningLogService;
import com.example.demo.service.WorldSKillDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wsdoc")
public class WorldSkillDocController {

    @Autowired
    WorldSKillDocService worldSKillDocService;

    @GetMapping("/download/{objectName}")
    public ResponseEntity<InputStreamResource> getFile(
            @PathVariable String objectName) {
        InputStream fileStream = worldSKillDocService.getDoc("wspdf", objectName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + objectName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }

    @PostMapping("/upload/{parentid}")
    public Result<String> uploadFile(@RequestPart("file") MultipartFile file,@PathVariable Integer parentid) {
        try {
            String newFileName =parentid+file.getOriginalFilename();
             worldSKillDocService.uploadDoc("wspdf", file,newFileName);
             return new Result<>(200,"文件上传成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<>(400,"文件上传失败",null);
    }

    @GetMapping("/getall")
    public Result<List<WorldSkillDoc>> getAllTrainLog() {
        return new Result<>(200,"获取成功",worldSKillDocService.getAllDoc());
    }

    @PostMapping("/uploadinfo")
    public Result<WorldSkillDoc> uploadInfo(@RequestBody WorldSkillDoc worldSkillDoc) {
        try
        {
            worldSKillDocService.setDoc(worldSkillDoc);
            return new Result<>(200,"新增成功",worldSkillDoc);
        }catch(Exception e)
        {
            return new Result<>(400,"新增失败",null);
        }



    }

}
