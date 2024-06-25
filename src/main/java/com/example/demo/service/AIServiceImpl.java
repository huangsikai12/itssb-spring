package com.example.demo.service;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AIServiceImpl implements AIService{

    @Override
    public String sendMessage(String msg) throws NoApiKeyException, InputRequiredException {
        Constants.apiKey="sk-c62a81d61a294c87a268b1b54529ebf5";
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();

        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(msg)
                .build();

        GenerationParam param = GenerationParam.builder()
                .model("qwen-turbo")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .build();
        return gen.call(param).getOutput().getChoices().get(0).getMessage().getContent();
    }
}
