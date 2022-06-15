package com.inoriii.hello.spring.web.aspect;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sakura
 * @date: 2022/6/8 22:07
 * @description:
 */
@Aspect
@Slf4j
@Component
public class ControllerLogAspect {

    @Pointcut("execution(public * com.inoriii.hello.spring.web.controller..*.*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("\n===============================Start========================");
        Object result = joinPoint.proceed();
        String logString = "\n" +
                "-------------------------- REQUEST --------------------------" + "\n" +
                "|IP                 : " + request.getRemoteAddr() + "\n" +
                "|URL                : " + request.getRequestURL().toString() + "\n" +
                "|HTTP Method        : " + request.getMethod() + "\n" +
                "|Class Method       : " + joinPoint.getSignature().getName() + "\n";
        if (getRequestParamsByProceedingJoinPoint(joinPoint).size() != 0) {
            logString += "|Request Params     : " + getRequestParamsByProceedingJoinPoint(joinPoint) + "\n";
        }
        logString += "|Result             : " + result + "\n" +
                "|TimeCost           : " + (System.currentTimeMillis() - start) + "\n" +
                "================================End=========================" + "\n";
        log.info(logString);
        return result;
    }

    @AfterThrowing(pointcut = "requestServer()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String logString = "\n" +
                "****************************** REQUEST ******************************" + "\n" +
                "|IP                 : " + request.getRemoteAddr() + "\n" +
                "|URL                : " + request.getRequestURL().toString() + "\n" +
                "|HTTP Method        : " + request.getMethod() + "\n" +
                "|Class Method       : " + joinPoint.getSignature().getName() + "\n";
        if (getRequestParamsByProceedingJoinPoint(joinPoint).size() != 0) {
            logString += "|Request Params     : " + getRequestParamsByProceedingJoinPoint(joinPoint) + "\n";
        }
        log.error(logString + "{}\n" +
                "*********************************End*********************************" + "\n", JSON.toJSONString(e));
    }


    /**
     * 获取入参
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(JoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

    @Data
    public class RequestInfo {
        private Object result;
        private Long timeCost;
    }

    @Data
    public class RequestErrorInfo {
        private RuntimeException exception;
    }
}
