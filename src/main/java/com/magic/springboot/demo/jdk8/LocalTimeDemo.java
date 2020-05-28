package com.magic.springboot.demo.jdk8;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDemo {
    public static void main(String[] args) {
        //表示当前时间
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间: " + localTime);

        //构建时间
        LocalTime localTime1 = LocalTime.of(10,10,10);
        System.out.println("构建时间: " + localTime1);

        //根据字符串转换时间--只能用默认格式，如果有其他格式需要自己使用DateTimeFormatter类来转换
        LocalTime localTime2 = LocalTime.parse("12:12:12");
        System.out.println("根据字符串转换时间:  " + localTime2);

        //自定义格式转换时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
        LocalTime localTime3 = LocalTime.parse("121212", dtf);
        System.out.println("根据字符串转换时间： " + localTime3);
        System.out.println("自定义格式输出时间: " + dtf.format(localTime3));

        //获取当前时间，不包含毫秒数
        LocalTime localTime4 = localTime.withNano(0);
        System.out.println("返回当前时间： " + localTime4);

        System.out.println("一个小时后: " + localTime.plusHours(1l));
        System.out.println("一个小时前: " + localTime.minusHours(1l));

    }
}
