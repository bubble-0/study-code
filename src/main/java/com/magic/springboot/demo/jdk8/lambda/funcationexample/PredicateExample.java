package com.magic.springboot.demo.jdk8.lambda.funcationexample;

import java.util.function.Predicate;

public class PredicateExample {
    private static void method_test(Predicate<String> predicate) {
        boolean b = predicate.test("ABC DEF");
        System.out.println(b);
    }

    private static void method_or(Predicate<String> predicate,Predicate<String> predicate2) {
        boolean b = predicate.or(predicate2).test("ABC DEF");
        System.out.println(b);
    }

    private static void method_and(Predicate<String> predicate,Predicate<String> predicate2) {
        boolean b = predicate.and(predicate2).test("ABC DEF");
        System.out.println(b);
    }

    private static void method_negate(Predicate<String> predicate) {
        boolean b = predicate.negate().test("ABC DEF");
        System.out.println(b);
    }



    public static void main(String[] args) {
        /**
         * 有时候我们需要对某种类型的数据进行判断，从而得到一个boolean值结果。
         * 这时可以使用java.util.function.Predicate<T> 接口
         *
         *  (s)->  函数式接口有参数 表示有有产生数据
         *
         *  (s)-> 具体的返回数据 看要是否原函数式接口给出了
         *
         * 抽象方法：test
         *
         * Predicate 接口中包含一个抽象方法： boolean test(T t) 。用于条件判断的场景：
         *
         * 默认方法：and or nagte (取反)
         *
         * 既然是条件判断，就会存在与、或、非三种常见的逻辑关系。其中将两个 Predicate 条件使用“与”逻辑连接起来实
         *
         * 现“并且”的效果时,类始于 Consumer接口 andThen()函数 其他三个雷同
         */

        method_test(i->i.contains("A"));

        method_or(i->i.contains("C"),i->i.contains("Z"));

        method_or(i->i.contains("X"),i->i.contains("Z"));

        method_and(i->i.contains("A"),i->i.contains("B"));

        method_and(i->i.contains("A"),i->i.contains("Z"));

        method_negate(i->i.contains("A"));
        method_negate(i->i.contains("Z"));

        //静态方法 isEqual 判断是否为同一个对象
        Predicate<String> predicate1 = i-> i.contains("A");
        Predicate<String> predicate3 = i-> i.contains("B");



        boolean b = Predicate.isEqual(predicate1).test(predicate1);
        System.out.println(b);

        System.out.println(Predicate.isEqual(predicate1).test(predicate3));


    }
}
