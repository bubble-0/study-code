package com.magic.springboot.demo.jdk8.methodreference;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MethodReferenceDemo {

    public static void main(String[] args) {

        //创建一个List , 将集合的元素翻转输出
        List<String> list = Arrays.asList("123","456","789");

        //1.通过普通lambda表达式实现
        List<String> list1 = DemoUtils.convert(list, i ->  new StringBuilder(i).reverse().toString());
        System.out.println(list1);

        //2.静态方法直接引用
        List<Integer> list2 = DemoUtils.convert(list, Integer::valueOf);
        System.out.println(list2);

        //3.非静态方法引用
        List<Integer> list3 = DemoUtils.convert(list, String::length);
        System.out.println(list3);

        //4.指定示例的方法引用
        Integer num = 2000;

        List<Integer> lists = Arrays.asList(1000,2000,3000);

        //lambda 方式
        List<Integer> results = DemoUtils.convert(lists, item->num.compareTo(item));
        System.out.println(results);

        //成员变量引用
        List<Integer> results2 = DemoUtils.convert(lists, num::compareTo);
        System.out.println(results2);

        //构造函数引用
        List<String> results3 = DemoUtils.convert(list, item -> LocalDate.now().toString());

        List<Date> results4 = DemoUtils.convert(lists, Date::new);
        //两种遍历方式
        results4.forEach(item->
            System.out.println(item.toString())
        );

        results4.forEach(System.out::println);
    }
}
