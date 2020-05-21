package com.magic.springboot.demo.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] args) {
        /**
         * eg:集合排序
         */

        // 准备一个集合
        List<Integer> list = Arrays.asList(10, 5, 25, -15, 20);

        //jdk1.7写法
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        List<Integer> list1 = Arrays.asList(10, 5, 25, -15, 20);

        //jdk1.8写法
        //list1.sort((i1,i2) -> {return i1 - i2;});
        //简写
        list1.sort((i1, i2) -> i1 - i2);

        System.out.println(list1);

        System.out.println("=================================");

        /**
         * eg:打印集合内容
         */

        //jdk1.7
        for (Integer i : list) {
            System.out.print(i + " , ");
        }

        System.out.println();

        //jdk1.8
        list.forEach((i) -> {
            System.out.print(i + " , ");
        });
        System.out.println();
        //简写
        list.forEach(i -> System.out.print(i + " , "));
        System.out.println();

        /**
         * eg:赋值
         * 把lambda表达式结果赋值给变量
         * 我们可以通过lambda表达式来实例化接口
         * 不过该用法很少见一般都是用来做参数
         */
        Runnable runnable = () -> {
            //直接使用匿名内部类编写run方法
            System.out.println("lambda  new runnable");
        };

        new Thread(runnable).start();

        //如果不用lambda表达式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("jdk 1.7 new runnable");
            }
        }).start();

        /**
         * eg:隐式final
         * Lambda表达式的实质其实还是匿名内部类，
         * 而匿名内部类在访问外部局部变量时，
         * 要求变量必须声明为final！
         * 不过我们在使用Lambda表达式时无需声明final，
         * 这并不是说违反了匿名内部类的规则，
         * 因为Lambda底层会隐式的把变量设置为final，
         * 在后续的操作中，一定不能修改该变量
         *
         * 为什么匿名内部类中要求变量必须是final的?
         * 内部类会自动拷贝外部变量的引用，
         * 为了避免：1. 外部方法修改引用，而导致内部类得到的引用值不一致
         * 2.内部类修改引用，而导致外部方法的参数值在修改前和修改后不一致。
         * 于是就用 final 来让该引用不可改变
         */
        int i = 0;

        //正确方式
        Runnable r = () -> {
            System.out.println("正确方式调用 :" + i);
        };
        new Thread(r).start();

        //错误方式,编译就会报错
       /* Runnable r2 = () -> {
            System.out.println("错误方式调用 :" + i ++);
        };
        new Thread(r2).start();*/



    }


}
