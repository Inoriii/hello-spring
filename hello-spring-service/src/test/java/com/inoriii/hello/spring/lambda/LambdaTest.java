package com.inoriii.hello.spring.lambda;

/**
 * @author sakura
 * @date: 2022/7/12 23:43
 * @description:
 */
public class LambdaTest {
    @FunctionalInterface
    public interface Sum {
        int sum(int a, int b);
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, Sum sum) {
        return sum.sum(a, b);
    }

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.add(1, 2, Integer::sum);
    }
}
