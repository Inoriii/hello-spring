package com.inoriii.hello.spring.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * resource_role_permission
 *
 * @author
 */
@Data
public class ResourceRolePermission implements Serializable {
    private Integer id;

    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}