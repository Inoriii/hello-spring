package com.inoriii.hello.spring.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * role_permission
 *
 * @author
 */
@Data
public class RolePermission implements Serializable {
    private Integer id;

    /**
     * 用户id
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