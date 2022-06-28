package com.inoriii.hello.spring.web.controller;

import com.inoriii.hello.spring.api.dto.AddUserDTO;
import com.inoriii.hello.spring.api.dto.FetchUserDTO;
import com.inoriii.hello.spring.api.service.TestService;
import com.inoriii.hello.spring.api.vo.FetchUserVO;
import com.inoriii.hello.spring.api.vo.Pager;
import com.inoriii.hello.spring.api.vo.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get/test-user")
    public RestResult<List<FetchUserVO>> getUser(@RequestParam String userName) {
        List<FetchUserVO> fetchUserVOList = testService.fetchUser(FetchUserDTO.builder().username(userName).build());
        return new RestResult<>(fetchUserVOList);
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

    @GetMapping("/getUserByAddresses")
    public Pager<FetchUserVO> getUserByAddresses(@RequestParam List<String> address,
                                                 @RequestParam long page,
                                                 @RequestParam long size) {
        return testService.getUserByAddresses(address, page, size);
    }
}

