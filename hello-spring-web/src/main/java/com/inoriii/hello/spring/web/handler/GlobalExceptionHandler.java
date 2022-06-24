package com.inoriii.hello.spring.web.handler;

import com.inoriii.hello.spring.api.enums.ResponseCode;
import com.inoriii.hello.spring.api.vo.RestResult;
import com.inoriii.hello.spring.common.exception.BusinessException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sakura
 * @date: 2021/8/1 16:36
 * @description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(BusinessException.class)
    public RestResult<Object> customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        BusinessException exception = (BusinessException) e;
        return new RestResult<>(exception.getCode(), exception.getMessage());
    }

    /**
     * 捕获  RuntimeException 异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public RestResult<Object> runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        return new RestResult<>(ResponseCode.FAIL);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult<Object> handleMissingServletRequestParameterException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
        return new RestResult<>(ResponseCode.FAIL.getCode(), message, null);
    }

    /**
     * Default Exception Handler
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResult<Object> handleException(Exception e, HttpServletRequest request) {
        return new RestResult<>(ResponseCode.FAIL);
    }
}
