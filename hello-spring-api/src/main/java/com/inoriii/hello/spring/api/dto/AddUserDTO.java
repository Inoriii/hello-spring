package com.inoriii.hello.spring.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author sakura
 * @date: 2021/8/1 16:15
 * @description:
 */
@Data
@Builder
public class AddUserDTO {
    @NotBlank(message = "username不能为空")
    private String username;
    @NotNull(message = "birthday不能为空")
    private LocalDate birthday;
    @NotBlank(message = "sex不能为空")
    private String sex;
    @NotBlank(message = "address不能为空")
    private String address;
}
