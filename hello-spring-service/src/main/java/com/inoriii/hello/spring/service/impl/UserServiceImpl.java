package com.inoriii.hello.spring.service.impl;

import com.inoriii.hello.spring.api.service.UserService;
import com.inoriii.hello.spring.api.vo.FetchUserRoleVO;
import com.inoriii.hello.spring.common.annotation.DataSource;
import com.inoriii.hello.spring.dao.mapper.UserMapper;
import com.inoriii.hello.spring.model.enums.DataSourceName;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sakura
 * @date: 2022/7/24 21:13
 * @description:
 */
@Service
@Log
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @DataSource(DataSourceName.SLAVE)
    public FetchUserRoleVO fetchUserRoleVO(String username) {
        return userMapper.selectFetchUserRoleVOByUsername(username);
    }
}
