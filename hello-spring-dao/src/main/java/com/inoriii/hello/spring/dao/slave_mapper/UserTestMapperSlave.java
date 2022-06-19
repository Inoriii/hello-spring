package com.inoriii.hello.spring.dao.slave_mapper;

import com.inoriii.hello.spring.model.entity.UserTest;

import java.util.List;

public interface UserTestMapperSlave {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);

    List<UserTest> selectByUserName(String username);
}