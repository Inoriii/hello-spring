package com.inoriii.hello.spring.service;

import com.inoriii.hello.spring.api.TestService;
import com.inoriii.hello.spring.common.utils.TestUtil;
import com.inoriii.hello.spring.dao.mapper.UserTestMapper;
import com.inoriii.hello.spring.model.dto.AddUserDTO;
import com.inoriii.hello.spring.model.entity.UserTest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author sakura
 * @date: 2022/6/8 22:02
 * @description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserTestMapper userTestMapper;

    @Override
    public void printMessage(String message) {
        System.out.println("Received message : " + message);
        System.out.println(TestUtil.addTestString(message));
    }

    @Override
    public void addUser(AddUserDTO addUserDTO) {
        UserTest userTest = new UserTest();
        BeanUtils.copyProperties(addUserDTO, userTest);
        userTestMapper.insertSelective(userTest);
    }

    @Override
    public Object getKey(String key) {
        return redisService.get(key);
    }
}
