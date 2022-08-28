package com.inoriii.hello.spring.web.config.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义投票器
 * 根据 角色和权限共同决定资源是否有权限访问
 * <p>
 * 权限组  Collection<ConfigAttribute> attributes中
 * 同一组中 角色与权限用"+"分割，为&&的关系
 * 不同组 为||的关系
 */
public class MyDecisionVoter implements AccessDecisionVoter<Object> {


    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (authentication == null || CollectionUtils.isEmpty(attributes)) {
            return ACCESS_DENIED;
        }
        int result = ACCESS_ABSTAIN;
        List<String[]> attributesList = new ArrayList<>();
        for (ConfigAttribute configAttribute : attributes) {
            String attribute = configAttribute.getAttribute();
            if (!StringUtils.hasText(attribute)) {
                continue;
            }
            attributesList.add(attribute.split("\\+"));
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> allAttributesList = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            if (!StringUtils.hasText(authority)) {
                continue;
            }
            allAttributesList.add(authority);
        }
        for (String[] attributeArray : attributesList) {
            boolean flag = true;
            for (String attribute : attributeArray) {
                if (!flag) {
                    break;
                }
                flag = allAttributesList.contains(attribute);
            }
            if (flag) {
                return ACCESS_GRANTED;
            }
        }
        return result;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}