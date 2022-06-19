package com.inoriii.hello.spring.web.controller;

import com.inoriii.hello.spring.api.TestService;
import com.inoriii.hello.spring.model.dto.AddUserDTO;
import com.inoriii.hello.spring.model.vo.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String printMessage(@RequestParam String message) {
        testService.printMessage(message);
        return "OK";
    }

    @PostMapping("/add/test-user")
    public String addUser(@RequestBody AddUserDTO addUserDTO) {
        testService.addUser(addUserDTO);
        return addUserDTO.toString();
    }

    @GetMapping("/error")
    public int printMessage() {
        int i = 1, j = 0;
        return i / j;
    }

    @GetMapping("/getMemory")
    public RestResult<Object> getMemory(@RequestParam String key) {
        Object o = testService.getKey(key);
        return new RestResult<>(o);
    }
}

