package com.inoriii.hello.spring.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author sakura
 * @date: 2021/8/1 16:15
 * @description:
 */
@Data
@Builder
public class ValidationDTO {
    @NotBlank(message = "username不能为空")
    private String username;
    @NotNull(message = "data")
    private LocalDate data;

    /**
     * 可以不传该参数，但是传了会被校验
     */
    @Valid
    private AddUserDTO addUserDTO;

    /**
     * 必须传该参数，内部没有被校验
     */
    @NotNull(message = "addUserNotNullDTO不能为空")
    private AddUserDTO addUserNotNullDTO;

    /**
     * 必须传该参数，内部被校验
     */
    @Valid
    @NotNull(message = "addUserDTOMax不能为空,内部对象也不能为空")
    private AddUserDTO addUserMaxDTO;

    /**
     * 可以不传该参数，但是传了会被校验
     */
    @Valid
    private List<AddUserDTO> addUserList;

    /**
     * 必须传该参数，内部没有被校验
     */
    @NotEmpty(message = "addUserNotNullDTOList不能为空")
    private List<AddUserDTO> addUserNotNullDTOList;

    /**
     * 必须传该参数，内部被校验
     */
    @Valid
    @NotEmpty(message = "addUserMaxDTOList不能为空,内部也不能为空")
    private List<AddUserDTO> addUserMaxDTOList;
}
