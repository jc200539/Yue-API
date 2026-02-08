package com.yue.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yue.sentinel.BlockHandlerService;
import com.yue.sentinel.FallbackService;
import com.yue.yueapiclientsdk.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称 API
 */
@RestController
@RequestMapping("/name")
public class NameController {


    @PostMapping("/user")
    @SentinelResource(value = "NameController",
            blockHandler = "NameControllerBlockHandler", blockHandlerClass = BlockHandlerService.class,
            fallback = "NameControllerFallback", fallbackClass = FallbackService.class)
    public String getUsernameByPost(@RequestBody User user) {
        return "POST 用户名字是" + user.getUsername();
    }
}