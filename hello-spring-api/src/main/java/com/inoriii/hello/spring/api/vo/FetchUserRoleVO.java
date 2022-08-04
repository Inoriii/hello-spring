package com.inoriii.hello.spring.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/7/24 21:08
 * @description:
 */
@Data
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
     * 用户角色
     */
    private List<RoleVO> roleList;
    /**
     * 用户权限
     */
    private List<PermissionVO> permissionList;

    @Data
    public static class RoleVO {

        /**
         * 权限id
         */
        private Integer roleId;
        /**
         * 权限名称
         */
        private String roleName;
    }

    @Data
    public static class PermissionVO {

        /**
         * 权限id
         */
        private Integer permissionId;
        /**
         * 权限名称
         */
        private String permissionName;
    }
}
