package com.inoriii.hello.spring.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/8/22 23:43
 * @description:
 */
@Data
public class FetchResourceVO {
    /**
     * 资源id
     */
    private Integer resourceId;
    /**
     * 资源id
     */
    private String resourceUrl;
    /**
     *
     */
    private List<ResourceRolePermissionVO> resourceRolePermissionList;

    @Data
    public static class ResourceRolePermissionVO {

        /**
         * 角色id
         */
        private Integer roleId;
        /**
         * 角色名称
         */
        private String roleName;
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
