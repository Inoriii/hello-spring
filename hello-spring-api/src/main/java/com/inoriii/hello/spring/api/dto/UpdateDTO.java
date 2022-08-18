package com.inoriii.hello.spring.api.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author sakura
 * @date: 2021/8/1 16:15
 * @description:
 */
@Data
@Builder
public class UpdateDTO {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
}
