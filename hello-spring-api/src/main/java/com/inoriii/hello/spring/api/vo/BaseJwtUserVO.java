package com.inoriii.hello.spring.api.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author sakura
 * @date: 2022/8/5 00:23
 * @description:
 */
@Data
@Builder
public class BaseJwtUserVO {
    private Integer userId;
    private String name;
}
