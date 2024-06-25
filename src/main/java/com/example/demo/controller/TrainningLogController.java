package com.example.demo.controller;


import com.example.demo.Result;
import com.example.demo.pojo.TrainLog;
import com.example.demo.service.TrainningLogService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/trainlog")
public class TrainningLogController {

    @Autowired
    TrainningLogService trainningLogService;

    @GetMapping("/download/{objectName}")
    public ResponseEntity<InputStreamResource> getFile(
            @PathVariable String objectName) {
        InputStream fileStream = trainningLogService.getFileLog("trainninglog", objectName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + objectName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }
    @GetMapping("/list")
    public Result<List<String>> listObjectNames() {
        return new Result<>(200,"获取成功",trainningLogService.listObjectNames("trainninglog"));
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        return trainningLogService.uploadFileLog("trainninglog", file);
    }

    @GetMapping("/getall")
    public Result<List<TrainLog>> getAllTrainLog() {
        return new Result<>(200,"获取成功",trainningLogService.getAllTrainLog());
    }

    @PostMapping("/uploadinfo")
    public Result<TrainLog> uploadInfo(@RequestBody TrainLog trainLog) {
        try
        {
            trainningLogService.setTrainLog(trainLog);
            return new Result<>(200,"新增成功",trainLog);
        }catch(Exception e)
        {
            return new Result<>(400,"新增失败",null);
        }



    }

}
