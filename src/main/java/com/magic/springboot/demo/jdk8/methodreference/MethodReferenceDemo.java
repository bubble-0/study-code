package com.magic.springboot.demo.jdk8.methodreference;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceDemo {

    public static void main(String[] args) {

        //创建一个List , 将集合的元素翻转输出
        List<String> list = Arrays.asList("123","456","789");

        //1.通过普通lambda表达式实现
        DemoUtils.convert(list, i ->  new StringBuilder(i).reverse());


    }
}
