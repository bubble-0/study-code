package com.magic.springboot.demo.jdk8.lambda;

import java.util.Optional;

public class OptionDemo {
    public static void main(String[] args) {

        /**
         * 如果Optional实例持有一个非空值，则isPresent()方法返回true，
         * 否则返回false；如果Optional实例持有null，
         * orElseGet()方法可以接受一个lambda表达式生成的默认值；
         * map()方法可以将现有的Optional实例的值转换成新的值,但是仅在不为空的情况
         * orElse()方法与orElseGet()方法类似，但是在持有null的时候返回传入的默认值，而不是通过Lambda来生成。
         */
        Optional<String> optional = Optional.ofNullable(null);
        Optional<String> optional1 = Optional.ofNullable("mike");
        System.out.println("isPresent : 为null " + optional.isPresent());
        System.out.println("isPresent : 不为null " + optional1.isPresent());

        System.out.println("orElsrGet 如果为null则返回: " + optional.orElseGet(()->"为空"));
        System.out.println("orElsrGet 如果不为null则返回: " + optional1.orElseGet(()->"为空"));

        //如果不为空才转换,为空则不转换
        System.out.println("map 方法转换: " + optional.map(s->"hello " + s + "!").orElse("为空,返回该结果"));
        System.out.println("map 方法转换: " + optional1.map(s->"hello " + s + "!").orElse("为空,返回该结果"));

        //需要注意of 方法不能为空 ofNullable 可以为空
        Optional< String > firstName = Optional.of( "Tom" );
        System.out.println( "First Name is set? " + firstName.isPresent() );
        System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) );
        System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        //Optional< String > firstName2 = Optional.of( null);

    }
}
