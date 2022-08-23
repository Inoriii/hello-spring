package com.inoriii.hello.spring.dao.mapper;

import com.inoriii.hello.spring.api.vo.FetchResourceVO;
import com.inoriii.hello.spring.model.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<FetchResourceVO> fetchAllResourceRolePermission();
}