package com.inoriii.hello.spring.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Validation2DTO {
    @NotBlank(message = "username不能为空")
    private String username;
    @NotNull(message = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
}
