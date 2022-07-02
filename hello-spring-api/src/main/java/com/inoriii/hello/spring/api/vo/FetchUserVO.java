package com.inoriii.hello.spring.api.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author sakura
 * @date: 2021/8/1 16:15
 * @description:
 */
@Data
@Builder
public class FetchUserVO {
    private String username;
    private LocalDate birthday;
    private String sex;
    private String address;
}
