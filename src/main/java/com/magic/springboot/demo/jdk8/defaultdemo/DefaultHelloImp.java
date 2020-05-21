package com.magic.springboot.demo.jdk8.defaultdemo;

public class DefaultHelloImp implements IDefaultHello,IDefaultHello2{
    //不需要在去重写声明了static 和 default的方法
    @Override
    public void sayHello() {
        System.out.println("say hello");
    }

    public static void main(String[] args) {
        DefaultHelloImp dh = new DefaultHelloImp();

        //重写的方法可以直接使用
        dh.sayHello();

        //defaule方法可以直接使用
        dh.defaultSayHello();

        //静态方法只能通过接口名来调用
        IDefaultHello.staticSayHello();

    }
}
