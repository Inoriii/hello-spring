package com.inoriii.hello.spring.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * role
 *
 * @author
 */
@Data
public class Role implements Serializable {
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否可用
     */
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}