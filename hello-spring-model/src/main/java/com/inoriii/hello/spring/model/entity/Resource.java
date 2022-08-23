package com.inoriii.hello.spring.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * resource
 * @author 
 */
@Data
public class Resource implements Serializable {
    private Integer id;

    /**
     * 资源url
     */
    private String url;

    /**
     * 是否可用
     */
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}