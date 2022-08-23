package com.inoriii.hello.spring.api.service;

import com.inoriii.hello.spring.api.vo.FetchResourceVO;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/8/22 23:40
 * @description:
 */
public interface ResourceService {
    List<FetchResourceVO> fetchAllResourceRolePermission();
}
