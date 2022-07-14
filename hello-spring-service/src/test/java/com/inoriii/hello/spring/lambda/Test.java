package com.inoriii.hello.spring.lambda;

/**
 * @author sakura
 * @date: 2022/7/14 19:16
 * @description:
 */
public class Test {
    int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    int test(Test b) {
        return i - b.i;
    }
}
