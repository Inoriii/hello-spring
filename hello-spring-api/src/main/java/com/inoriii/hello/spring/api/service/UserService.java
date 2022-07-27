package com.inoriii.hello.spring.api.service;

import com.inoriii.hello.spring.api.vo.FetchUserRoleVO;

/**
 * @author sakura
 * @date: 2022/7/24 21:07
 * @description:
 */
public interface UserService {
    /**
     * 根据username查询权限信息
     * @param username username
     * @return FetchUserRoleVO
     */
    FetchUserRoleVO fetchUserRoleVO(String username);
}
