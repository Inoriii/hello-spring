package com.inoriii.hello.spring.service.impl;

import com.inoriii.hello.spring.api.vo.FetchResourceVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/8/23 00:07
 * @description:
 */
@SpringBootTest
class ResourceServiceImplTest {
    @Autowired
    private ResourceServiceImpl resourceService;

    @Test
    public void testAddUser() {
        List<FetchResourceVO> fetchResourceVOS =
                resourceService.fetchAllResourceRolePermission();
        System.out.println(fetchResourceVOS);
    }
}