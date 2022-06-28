package com.inoriii.hello.spring.dao.mapper;

import com.inoriii.hello.spring.api.dto.AddUserDTO;
import com.inoriii.hello.spring.model.entity.UserTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);

    List<UserTest> selectByUserName(String username);

    void insertUserDTOList(List<AddUserDTO> addUserDTOList);

    List<UserTest> selectByAddress(@Param("addresses") List<String> address, @Param("offset") long offset, @Param("size") long size);

    long selectByAddressCount(@Param("addresses") List<String> address);
}