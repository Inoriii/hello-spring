package com.inoriii.hello.spring.lambda;

/**
 * @author sakura
 * @date: 2022/7/14 19:17
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        int test = test(Test::test);
        System.out.println(test);
    }

    public static int test(TestInterface testInterface) {
        Test a = new Test();
        a.i = 10;
        Test b = new Test();
        b.i = 11;
        int test = testInterface.test(a, b);
        return test;
    }
}
