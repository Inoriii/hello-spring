package com.inoriii.hello.spring.common.exception;


import com.inoriii.hello.spring.api.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sakura
 * @date: 2021/8/1 16:32
 * @description:
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private int code;
    private String message;

    public BusinessException(ResponseCode responseCode) {
        code = responseCode.getCode();
        message = responseCode.getMsg();
    }
}
