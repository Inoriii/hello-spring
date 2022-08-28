package com.inoriii.hello.spring.web.config.security;

import com.inoriii.hello.spring.api.service.ResourceService;
import com.inoriii.hello.spring.api.vo.FetchResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author sakura
 * @date: 2022/8/21 22:27
 * @description: 自定义数据源
 * 多组url匹配取第一组
 * 取得的FetchResourceVO.ResourceRolePermissionVO中，将其中的权限与角色用+拼接，且为&&的关系。
 * 多个FetchResourceVO.ResourceRolePermissionVO为||关系
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private ResourceService resourceService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<FetchResourceVO> fetchResourceVOList = resourceService.fetchAllResourceRolePermission();
        if (CollectionUtils.isEmpty(fetchResourceVOList)) {
            return null;
        }
        List<String> allRolePermissionNameList = new ArrayList<>();
        for (FetchResourceVO fetchResourceVO : fetchResourceVOList) {
            if (new AntPathMatcher().match(fetchResourceVO.getResourceUrl(), requestUrl)) {
                List<FetchResourceVO.ResourceRolePermissionVO> resourceRolePermissionList = fetchResourceVO.getResourceRolePermissionList();
                if (CollectionUtils.isEmpty(resourceRolePermissionList)) {
                    continue;
                }
                //拼接权限。形如
                // ROLE_董事长+超级管理员
                // ROLE_董事长
                // 超级管理员
                StringBuilder sb = new StringBuilder();
                for (FetchResourceVO.ResourceRolePermissionVO resourceRolePermissionVO : resourceRolePermissionList) {
                    if (StringUtils.hasText(resourceRolePermissionVO.getRoleName())) {
                        sb.append("ROLE_").append(resourceRolePermissionVO.getRoleName());
                    }
                    boolean hasPermission = StringUtils.hasText(resourceRolePermissionVO.getPermissionName());
                    if (!hasPermission) {
                        String rolePermissionString = sb.toString();
                        if (StringUtils.hasText(rolePermissionString)) {
                            allRolePermissionNameList.add(rolePermissionString);
                        }
                        continue;
                    }
                    if (sb.length() != 0) {
                        sb.append("+");
                    }
                    sb.append(resourceRolePermissionVO.getPermissionName());
                    String rolePermissionString = sb.toString();
                    if (StringUtils.hasText(rolePermissionString)) {
                        allRolePermissionNameList.add(rolePermissionString);
                    }
                }
                break;
            }
        }
        return SecurityConfig.createList(allRolePermissionNameList.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
