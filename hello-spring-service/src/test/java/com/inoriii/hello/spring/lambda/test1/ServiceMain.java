package com.inoriii.hello.spring.lambda.test1;

/**
 * @author sakura
 * @date: 2022/7/14 19:17
 * @description:
 */
public class ServiceMain {
    public static void main(String[] args) {
        //动态方法以静态方式调用
        int test = doSomething(ServiceA::process);
        System.out.println(test);

        //动态方法以动态方式调用,和上面调用的不是一个方法，且结果也不同
        test = doSomething(new ServiceA()::process);
        System.out.println(test);
    }

    public static int doSomething(Service service) {
        ServiceA a = new ServiceA();
        a.i = 10;
        ServiceB b = new ServiceB();
        b.i = 11;
        int test = service.doSomething(a, b);
        return test;
    }
}
