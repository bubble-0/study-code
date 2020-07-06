package com.magic.springboot.demo.jdk8.lambda.funcationexample;

import java.util.function.Function;

public class FuncationExample {
    public static String method_apply(Function<Integer, String> function) {
        return function.apply(11);
    }

    //andThen 先计算前面的funcaion,在计算后面的
    private static Integer method_andThen(Function<Integer, Integer> function, Function<Integer, Integer> function2) {
        return function.andThen(function2).apply(11);
    }

    //compose 先计算后面的,在计算前面的
    private static Integer method_ccompose(Function<Integer, Integer> function, Function<Integer, Integer> function2) {
        return function.compose(function2).apply(11);
    }

    public static void main(String[] args) {
        /**
         * java.util.function.Function<T,R> 接口用来根据一个类型的数据得到另一个类型的数据，
         * 前者称为前置条件，后者称为后置条件 T是参数R是返回值
         */

        //直接使用
        System.out.println(method_apply(i -> i.toString()));

        /**
         * 默认方法 andThen  compose():
         * Function 接口中有一个默认的 andThen  compose方法，用来进行组合操作。
         * JDK源代码如：
         *  default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
         *      Objects.requireNonNull(after);
         *      return (T t) -> after.apply(apply(t));// 先执行调用者,再执行after的apply犯法
         *  }  // 这里的V 一个是作为输入值 一个是作为输出值  按照调用的顺序的不同 对于 T V 做输入 输出的顺序也不同 注意看
         *
         *  default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
         *      Objects.requireNonNull(before);
         *      return (V v) -> apply(before.apply(v));// 后执行before的apply方法,后执行调用者apply方法
         *  }
         */

        //使用组合
        System.out.println(method_andThen(
                i -> i * 2,
                i -> i + 2
        )); //先*2 在+2  结果是24


        System.out.println(method_ccompose(
                i -> i * 2,
                i -> i + 2
        )); //先+2 在*2  结果是26

        //返回输入参数的函数对象
        Object apply = Function.identity().apply(2);
        System.out.println(apply);

    }
}
