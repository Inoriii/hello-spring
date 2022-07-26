package com.inoriii.hello.spring.api.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/7/24 21:08
 * @description:
 */
@Data
@Builder
public class FetchUserRoleVO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户密码
     */
    private List<FetchRoleVO> roleList;

    @Data
    @Builder
    public static class FetchRoleVO {

        /**
         * 权限id
         */
        private Integer roleId;
        /**
         * 权限名称
         */
        private String roleName;
    }
}
