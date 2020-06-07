package com.magic.springboot.demo.jdk8.lambda.funcationexample;

import java.util.Arrays;
import java.util.function.Supplier;

public class SupplierExample {
    SupplierExample(){
        System.out.println("调用SupplierExample构造方法");
    }
    
    private static String test_Supplier(Supplier<String> suply) {
        return suply.get();
    }

    private static Integer getArrMax(Supplier<Integer> maxInt) {
        return maxInt.get();
    }

    public static void main(String[] args) {
        /**
         *  Supplier接口(供应接口) 英文翻译就是“供应者”，顾名思义：只产出，不收取。所以不接受任何参数，返回T类型结果。
         *  java.util.function.Supplier<T> 接口仅包含一个无参的方法： T get() 。
         *  用来获取一个泛型参数指定类型的对象数据。由于这是一个函数式接口
         *  这也就意味着对应的Lambda表达式需要“对外提供”一个符合泛型类型的对象数据。
         *  需要调用get方法才能获取定义的值
         */
        System.out.println(test_Supplier(() ->
            "test_Supplier 返回的字符串"
        ));

        //一个小总结，只要类型确定了就可以使用lambda表达式来写
        Supplier<String> str = () -> "lambda返回的字符串";
        System.out.println(str.get());

        //或者这样，只要满足函数式表达式的情况就可以
        System.out.println(((Supplier<String>) () -> "匿名内部类返回的字符串").get());


        /**
         * Supplier容器使用
         * 创建Supplier容器，每次调用get方法都会重新实例化一个对象，调用对象的构造方法
         */

        //创建容器，此时并不会实例化对象
        Supplier<SupplierExample> supplierExamples = SupplierExample::new;

        //调用构造方法
        SupplierExample supplierExample1 = supplierExamples.get();

        //调用构造方法
        SupplierExample supplierExample2 = supplierExamples.get();

        //结果并不相等
        System.out.println(supplierExample1 == supplierExample2);


        /**
         * 练习：求数组元素最大值
         */
        int arr [] = {13,32,35,47,5,62,7,28,101,11};

        System.out.println("数据元素最大值" +
        getArrMax(() -> {
            Arrays.sort(arr);
            return arr[arr.length - 1 ];
        }));

    }
}
