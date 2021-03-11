package com.magic.springboot.demo.javademo.collectdemo;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        //list1.add("3");

        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");
        list2.add("5");

        //交集
        list1.retainAll(list2);
        System.out.println(list1);

        System.out.println("0".toUpperCase());

    }
}
