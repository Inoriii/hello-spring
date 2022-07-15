package com.inoriii.hello.spring.lambda.test1;

/**
 * @author sakura
 * @date: 2022/7/14 19:16
 * @description:
 */
public class ServiceA {
    int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    int process(ServiceB b) {
        return i - b.i;
    }

    int process(ServiceA serviceImpl1, ServiceB b) {
        return i - b.i;
    }
}
