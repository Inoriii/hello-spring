package com.inoriii.hello.spring.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * permission
 *
 * @author
 */
@Data
public class Permission implements Serializable {
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 是否可用
     */
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}