package com.example.demo.service;

import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.stereotype.Service;

@Service
public interface AIService {

     String sendMessage(String msg) throws NoApiKeyException, InputRequiredException;

}
