package com.magic.springboot.demo.jdk8.lambda;

/**
 * lambda 表达式的懒加载案例
 */
public class LazzyLoadingDemo {
    //只有在级别1的情况下才会打印日志
    public static void log(int level, String mes) {
        if (level == 1) {
            System.out.println(mes);
        }
    }

    public static void lambdalog(int level, MessageBuild messageBuild) {
        if (level == 1) {
            System.out.println(messageBuild.buildMessage()); //利用内部类懒加载的原理此处先不执行，把计算方式在调用时利用匿名内部类的方式执行
        }
    }

    public static void main(String[] args) {
        String msgA = "HELLO ";
        String msgB = "LAMBDA";
        String msgC = "FUNCATIONINTERFACE";

        //级别1的条件是不一定达到的，但是后面的字符串还是会处理
        log(1, msgA + msgB + msgC);

        lambdalog(1, () ->{
            System.out.println("lambda is excute 1");
            return msgA + msgB + msgC;}
        );

        lambdalog(2, () ->{
            System.out.println("lambda is excute 2");
            return msgA + msgB + msgC;}
        );

    }
}

/**
 * lambda函数式接口优雅写法
 */
@FunctionalInterface
interface MessageBuild {

    String buildMessage();
}
