package com.inoriii.hello.spring.lambda;

/**
 * @author sakura
 * @date: 2022/7/12 23:43
 * @description:
 */
public class LambdaTest {
    int i = 1;

    @FunctionalInterface
    public interface Sub {
        int sub(int a);
    }

    public int compute(int j) {
        return j - i;
    }

    public int compute(int a, Sub subtr) {
        return subtr.sub(a);
    }

    @FunctionalInterface
    public interface Sum {
        int sum(int a, int b);
    }

    public static int compute(int a, int b) {
        return a + b;
    }

    public int compute(int a, int b, Sum sum) {
        return sum.sum(a, b);
    }

    public static void main(String[] args) {
        //1、有接口
        //2、有相同参数列表
        //3、相同返回值的方法，有调用（1）的接口
        //可以不用实现接口，可以不用相同方法名------------因为只有一个抽象方法，所以明确知道调用的方法是什么
        LambdaTest lambdaTest = new LambdaTest();
        int add = lambdaTest.compute(1, 2, LambdaTest::compute);
        int sub = lambdaTest.compute(3, lambdaTest::compute);
        int mult = lambdaTest.compute(1, 2, (a, b) -> a * b);

        System.out.println("add: " + add + "\nsub: " + sub + "\nmult: " + mult);
    }
}
