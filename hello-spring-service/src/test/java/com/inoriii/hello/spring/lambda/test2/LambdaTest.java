package com.inoriii.hello.spring.lambda.test2;

/**
 * @author sakura
 * @date: 2022/7/12 23:43
 * @description:
 */
public class LambdaTest {
    protected int num;

    @FunctionalInterface
    public interface Plus {
        int plus(int num1, int num2);
    }

    public static int computePlus(int num1, int num2, Plus plus) {
        return plus.plus(num1, num2);
    }

    @FunctionalInterface
    public interface Minus {
        int minus(int num1);
    }

    public static int computeMinus(int num1, Minus minus) {
        return minus.minus(num1);
    }

    public int compute(int num1) {
        return num - num1;
    }

    @FunctionalInterface
    public interface Multiply {
        int multiply(LambdaTest a, LambdaTest b);
    }

    public static int computeMultiply(LambdaTest a, LambdaTest b, Multiply multiply) {
        return multiply.multiply(a, b);
    }

    public static int compute(LambdaTest a, LambdaTest b) {
        return a.num * b.num;
    }

    @FunctionalInterface
    public interface Divided {
        int divided(LambdaTest a, OtherClass b);
    }

    public static int computeDivided(LambdaTest a, OtherClass b, Divided divided) {
        return divided.divided(a, b);
    }

    public int compute(OtherClass arg) {
        return arg.num / num;
    }

    /**
     * 1. 有一个@FunctionalInterface接口
     * 2. 有一个方法（该方法所在类可以不实现接口，也可以与接口方法名不同
     * ，因为只有一个抽象方法，所以明确知道调用的方法是什么）
     * 3. 接口实现函数为静态方法，必须需要有相同参数列表,参考【multiply】
     * 4. 接口实现函数为非静态方法
     * 4.1 以非静态方式掉用时，即new LambdaTest()::compute，必须需要有相同参数列表
     * 4.2 以静态方式掉用时，即LambdaTest::compute，必须比接口少一个参数
     * ，相当于将【非静态方法】转为【静态方法】，并作为第一个参数加入进来，参考下面例子的divided
     *
     * @param args
     */
    public static void main(String[] args) {
        //函数调用
        int plus = computePlus(1, 2, ((num1, num2) -> num1 + num2 + 1 - 1));
        System.out.println("plus: " + plus);

        //静态方法调用
        int multiply = computeMultiply(new LambdaTest() {{
            num = 5;
        }}, new LambdaTest() {{
            num = 6;
        }}, LambdaTest::compute);
        System.out.println("multiply: " + multiply);

        //非静态方法调用,以非静态方式调用，即new LambdaTest()::compute
        int minus = computeMinus(3, new LambdaTest() {{
            num = 2;
        }}::compute);
        System.out.println("minus: " + minus);

        //非静态方法调用,以静态方式调用，即LambdaTest::compute
        int divided = computeDivided(new LambdaTest() {{
            num = 5;
        }}, new OtherClass() {{
            num = 6;
        }}, LambdaTest::compute);
        System.out.println("divided: " + divided);
    }

}
