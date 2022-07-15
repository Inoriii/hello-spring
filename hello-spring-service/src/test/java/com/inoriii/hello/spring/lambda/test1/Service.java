package com.inoriii.hello.spring.lambda.test1;

/**
 * @author sakura
 * @date: 2022/7/14 19:16
 * @description:
 */
@FunctionalInterface
public interface Service {
    int doSomething(ServiceA a, ServiceB b);
}
