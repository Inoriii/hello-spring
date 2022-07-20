package com.inoriii.hello.spring.web.controller;

import com.inoriii.hello.spring.api.dto.Validation2DTO;
import com.inoriii.hello.spring.api.dto.ValidationDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author sakura
 * @date: 2022/7/20 21:59
 * @description:
 */
@RestController
@RequestMapping("/validation")
@Validated
public class TestValidationController {
    @PostMapping("/post/requestBody")
    public String postValidation(@RequestBody @Validated ValidationDTO validationDTO) {
        return "ok";
    }

    @GetMapping("/get/{id}")
    public String getValidation(@PathVariable("id") String id,
                                @RequestParam(value = "email") @Email(message = "email格式不正确") String email) {
        return "ok";
    }

    /**
     * Form校验
     */
    @PostMapping(value = "/post/form")
    public String test2(@Validated Validation2DTO validVO) {
        return "ok";
    }

    /**
     * 单参数校验
     */
    @PostMapping(value = "/post/alone")
    public String test3(@NotBlank(message = "email不能为空") @Email String email) {
        return "ok";
    }

}
