package com.inoriii.hello.spring.dao.mapper;

import com.inoriii.hello.spring.model.entity.ResourceRolePermission;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceRolePermission record);

    int insertSelective(ResourceRolePermission record);

    ResourceRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceRolePermission record);

    int updateByPrimaryKey(ResourceRolePermission record);
}