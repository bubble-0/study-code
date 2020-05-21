package com.magic.springboot.demo.jdk8.defaultdemo;

public interface IDefaultHello {

    void sayHello();

    static void staticSayHello(){
        System.out.println("static  hello");
    }

    default void defaultSayHello(){
        System.out.println("default  hello");
    }
}
