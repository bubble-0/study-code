package com.magic.springboot.demo.jdk8.lambda;

/**
 * java 8 函数式接口调用
 */
public class FunctionInterfaceDemo {

    //将函数式接口当做参数
    public static void excuteMyFunctionMethod(MyFuncationInterface myFuncationInterface) {
        myFuncationInterface.myFuncationMethod();
    }

    public static void main(String[] args) {
        /**
         * 调用函数式接口,和之前匿名内部类调用的方式同理
         */
        excuteMyFunctionMethod(() ->
            System.out.println("执行MyFuncationMethod")
        );

    }
}
