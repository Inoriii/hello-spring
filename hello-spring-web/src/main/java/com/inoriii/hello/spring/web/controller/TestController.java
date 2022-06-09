package com.inoriii.hello.spring.web.controller;

import com.inoriii.hello.spring.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sakura
 * @date: 2022/6/8 21:38
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/print/message")
    public String addUser(@RequestParam String message) {
        testService.addUser(message);
        return "OK";
    }
}

