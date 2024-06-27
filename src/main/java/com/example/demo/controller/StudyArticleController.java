package com.example.demo.controller;


import com.example.demo.Result;
import com.example.demo.pojo.StudyArticle;
import com.example.demo.pojo.User;
import com.example.demo.service.StudyArticleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/study/article")
public class StudyArticleController {

    @Autowired
    StudyArticleService studyArticleService;


    @GetMapping("/getarticle/{type}")
    public Result<List<StudyArticle>> getArticle(@PathVariable String type)
    {
        List<StudyArticle> studyArticleList;
        try {
            studyArticleList =  studyArticleService.getArticleByType(type);
            if(studyArticleList.isEmpty())
            {
                return new Result<>(200,"获取文章列表为空",null);
            }
            return new Result<>(200,"获取文章列表成功",studyArticleList);
        }
        catch (Exception e)
        {
            return new Result<>(404,"获取文章列表失败",null);
        }

    }
    @GetMapping("/getarticlevideourl/{file_name}")
    public Result<String> getarticlevideourl(@PathVariable String file_name)
    {

        try {
            String url = studyArticleService.getArticleVideoUrl("studyvideo",file_name);
            if(url.isEmpty())
            {
                return new Result<>(200,"获取内容为空",null);
            }
            return new Result<>(200,"获取url成功",url);
        }
        catch (Exception e)
        {
            return new Result<>(404,"获取内容失败",null);
        }

    }
}
