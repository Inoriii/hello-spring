package com.inoriii.hello.spring.web.handler;

import com.inoriii.hello.spring.api.enums.ResponseCode;
import com.inoriii.hello.spring.api.vo.RestResult;
import com.inoriii.hello.spring.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author sakura
 * @date: 2021/8/1 16:36
 * @description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * PostMapping:
     * BindException:Content-Type:
     * Content-Type: application/x-www-form-urlencoded 表单提交
     * MethodArgumentNotValidException:
     * Content-Type: application/json requestBody为json
     * ConstraintViolationException：
     * Content-Type: application/x-www-form-urlencoded 单参数校验
     */
    public static final Map<Class<? extends Exception>, Function<Exception, String>> VALIDATION_ERROR_MAP = new ConcurrentHashMap<Class<? extends Exception>, Function<Exception, String>>(8) {{
        put(ConstraintViolationException.class, k -> ((ConstraintViolationException) k).getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; ")));
        put(MethodArgumentNotValidException.class, k -> ((MethodArgumentNotValidException) k).getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
        put(BindException.class, k -> ((BindException) k).getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; ")));
    }};

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public RestResult<Object> handleMissingServletRequestParameterException(Exception e) {
        AtomicReference<String> message = new AtomicReference<>("参数错误");
        VALIDATION_ERROR_MAP.forEach((key, value) -> {
            if (key.isAssignableFrom(e.getClass())) {
                message.set(value.apply(e));
            }
        });
        return new RestResult<>(ResponseCode.FAIL.getCode(), message.get(), null);
    }

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
        log.error("error:" + e);
        return new RestResult<>(ResponseCode.FAIL);
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
