package com.magic.springboot.demo.javademo.enumdemo;

/**
 * jdk1.7后switch支持枚举类型
 */
public class SwitchEnumDemo {
    static void printStatus(Status status) {
        switch (status) {
            case OK:
                System.out.println("switch ok ");
                break;
            case FAIL:
                System.out.println("switch fail");
                break;
            default:
                System.out.println("default init");
                break;
        }
    }

    public static void main(String[] args) {
        printStatus(Status.OK);
        printStatus(Status.FAIL);
        printStatus(Status.INIT);

    }
}

enum Status{
    INIT,FAIL,OK
}
