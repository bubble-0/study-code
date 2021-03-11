package com.magic.springboot.demo.jdk8.stream;

import java.util.Random;
import java.util.stream.Stream;

class Person {
    private String name;

    private int age;

    private String sex;

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

public class CreateStreamDemo {
    public static void main(String[] args) {
        /**
         * 1.创建一个有限的Integer流
         */
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5);
        System.out.println("of(T ...value)");
        s1.forEach(System.out::print);
        System.out.println();

        /**
         * 2.创建一个无限长度Integer流,generate 参数是一个supplier接口,可以使用lambda表达式
         */
        //Stream<Integer> s2 = Stream.generate(()->new Integer(11));
        //第二种写法
        Stream<String> s2 = Stream.generate(String::new);

        //创建一个无限长度的stream
        Stream<Integer> s3 = Stream.generate(new Random()::nextInt);
        s3.limit(5).forEach(System.out::print);
        System.out.println();

        /**
         * 3.创建一个无限长度的stream
         */
        Stream<Integer> s4 = Stream.iterate(0, i -> i + 1);
        s4.limit(5).forEach(System.out::print);
        System.out.println();

        /**
         * 4.合并形成新的流
         */
        Stream<Integer> s6 = Stream.concat(Stream.of(1, 2, 3, 4, 5), Stream.of(6, 7, 8, 9));
        s6.forEach(System.out::print);
        System.out.println();

        /**
         * 5.单个元素
         */
        Stream<Integer> s7 = Stream.of(1);
        s7.forEach(System.out::print);

        /**
         * 6.返回空的流
         */
        Stream<Integer> s8 = Stream.empty();
        System.out.println(s8.count());




    }
}
