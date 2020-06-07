package com.magic.springboot.demo.jdk8.defaultd;

public interface IDefaultHello2 {

    void sayHello();

    static void staticSayHello(){
        System.out.println("static  hello");
    }

    default void defaultSayHello2(){
        System.out.println("default  hello");
    }
}
