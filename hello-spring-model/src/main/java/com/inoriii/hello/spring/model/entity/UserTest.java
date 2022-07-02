package com.inoriii.hello.spring.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * user_test
 *
 * @author
 */
@Data
public class UserTest implements Serializable {
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}