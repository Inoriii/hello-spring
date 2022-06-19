package com.inoriii.hello.spring.api;

import com.inoriii.hello.spring.model.dto.AddUserDTO;
import com.inoriii.hello.spring.model.dto.FetchUserDTO;
import com.inoriii.hello.spring.model.entity.FetchUserVO;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/6/8 21:38
 * @description:
 */
public interface TestService {
    /**
     * 打印message
     *
     * @param message message
     */
    void printMessage(String message);

    /**
     * 插入测试用户
     *
     * @param addUserDTO
     */
    void addUser(AddUserDTO addUserDTO);

    List<FetchUserVO> fetchUser(FetchUserDTO addFetchUserDTO);

    Object getKey(String key);
}
