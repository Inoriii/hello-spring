package com.inoriii.hello.spring.service.impl;

import com.inoriii.hello.spring.api.service.ResourceService;
import com.inoriii.hello.spring.api.vo.FetchResourceVO;
import com.inoriii.hello.spring.dao.mapper.ResourceMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/8/22 23:40
 * @description:
 */
@Service
@Log
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;


    @Override
    public List<FetchResourceVO> fetchAllResourceRolePermission() {
        return resourceMapper.fetchAllResourceRolePermission();
    }
}
