package com.magic.springboot.demo.jdk8.lambda.funcationexample;

import java.util.function.Consumer;

/**
 * java.util.function.Consumer<T> 接口则正好与Supplier接口相反，
 * 它不是生产一个数据，而是消费一个数据，其数据类型由泛型决定
 * <p>
 * 提供了一个默认方法andThen
 * 如果一个方法的参数和返回值全都是 Consumer 类型，
 * 那么就可以实现效果：消费数据的时候，首先做一个操作，然后再做一个操作，实现组合
 * <p>
 * default Consumer<T> andThen(Consumer<? super T> after) {
 * Objects.requireNonNull(after);
 * return (T t) ‐> { accept(t); after.accept(t); };
 * //1:  返回值为Consumer 那么需要 ()-> 表示函数式接口
 * //2:  accept(t);为生产一个数据供应给 (T t)中的t
 * //3:  after.accept(t);为利用这个t再次生成新的函数式接口 实现类始于builder的设计模式
 * }
 * <p>
 * 执行顺序，先用lambda实现逻辑之后，执行accept函数执行lambda方法，即重写的accept方法
 */
public class ConsumerExample {
    public static void generateX(Consumer<String> consumer) {
        consumer.accept("hello consumer");
        consumer.accept("secon consumer");
        consumer.accept("hi consumer");
    }

    public static void formatPersonMsg(Consumer<String[]> con1, Consumer<String[]> con2) {
        //组合作用。使用andThen方法，con1.andThen(con2).accept();会使两者都消费
        con1.andThen(con2).accept(new String[]{"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男"});
    }

    public static void main(String[] args) {
        generateX(s -> System.out.println(s));

        formatPersonMsg(s1 -> {
            for (String s : s1) {
                System.out.print(s.split("\\,")[0] + " ");
            }
            System.out.println();
        }, s2 -> {
            for (String s : s2) {
                System.out.print(s.split("\\,")[1] + " ");
            }
        });

        System.out.println();

        printInfo(r -> System.out.println(r.split("\\,")[0]),
                r -> System.out.println(r.split("\\,")[1]), new String[]{"小明,12", "小红,15", "小让,128"});

    }

    //自产自销
    private static void printInfo(Consumer<String> con1, Consumer<String> con2, String[] arr) {
        for (String s : arr) {
            con1.andThen(con2).accept(s);
        }
    }
}
