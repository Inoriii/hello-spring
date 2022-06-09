package com.inoriii.hello.spring.service;

import com.inoriii.hello.spring.api.TestService;
import com.inoriii.hello.spring.common.utils.TestUtil;
import org.springframework.stereotype.Service;


/**
 * @author sakura
 * @date: 2022/6/8 22:02
 * @description:
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void addUser(String message) {
        System.out.println("Received message : " + message);
        System.out.println(TestUtil.addTest(message));
    }
}
