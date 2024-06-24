package com.example.demo.controller;


import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.example.demo.Result;
import com.example.demo.service.AIService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ai")
public class AIController {

    @Resource
    AIService aiService;

    @GetMapping("/ask")
    public Result<String> getAsk(String msg) throws NoApiKeyException, InputRequiredException {
        if (msg.length()!=0)
        {
            return new Result<String >(200,"发送成功",aiService.sendMessage(msg));
        }
        return new Result<String >(400,"消息不能为空",aiService.sendMessage(msg));

    }

}
