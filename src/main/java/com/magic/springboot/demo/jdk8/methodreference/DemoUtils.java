package com.magic.springboot.demo.jdk8.methodreference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DemoUtils {

    /**
     * 转换函数，将list集合的每一个元素进行转换
     * list 要转换的集合lsit
     * function 函数接口，接收T 返回 R
     */
    public static <T,R> List<R> convert(List<T> list, Function<T,R> function) {
        List<R> result = new ArrayList<>();

        list.forEach(t ->result.add(function.apply(t)));
        return result;
    }
}
