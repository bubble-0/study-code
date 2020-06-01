package com.magic.springboot.demo.javademo.enumdemo;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * EnumSet是与枚举类型一起使用的专用 Set 集合，EnumSet 中所有元素都必须是枚举类型。
 * 与其他Set接口的实现类HashSet/TreeSet(内部都是用对应的HashMap/TreeMap实现的)不同的是，
 * EnumSet在内部实现是位向量，它是一种极为高效的位运算操作，
 * 由于直接存储和操作都是bit，因此EnumSet空间和时间性能都十分可观，
 * 足以媲美传统上基于 int 的“位标志”的运算
 */
public class EnumSetDemo {
    public static void main(String[] args) {
        /**
         * 创建EnumSet并不能使用new关键字，因为它是个抽象类，
         * 而应该使用其提供的静态工厂方法，EnumSet的静态工厂方法比较多
         */

        //创建一个空集合
        EnumSet<ColorSetEnum> enumSet= EnumSet.noneOf(ColorSetEnum.class);
        System.out.println("添加前: " + enumSet);

        enumSet.add(ColorSetEnum.RED);
        enumSet.add(ColorSetEnum.BLUE);
        enumSet.add(ColorSetEnum.GREEN);
        System.out.println("添加后： " + enumSet);

        //使用allOf创建包含所有枚举类型的enumSet，其内部根据Class对象初始化了所有枚举实例
        EnumSet<ColorSetEnum> enumSet1= EnumSet.allOf(ColorSetEnum.class);
        System.out.println("使用allOf直接填充: " + enumSet1);

        //初始集合包括枚举值中指定范围的元素
        EnumSet<ColorSetEnum> enumSet2=EnumSet.range(ColorSetEnum.RED,ColorSetEnum.BLUE);
        System.out.println("使用range自定义 ：" + enumSet2);

        //指定补集，也就是从全部枚举类型中去除参数集合中的元素，如下去掉上述enumSet2的元素
        EnumSet<ColorSetEnum> enumSet3=EnumSet.complementOf(enumSet2);
        System.out.println("补集元素: " + enumSet3);

        //初始化时直接指定元素
        EnumSet<ColorSetEnum> enumSet4 = EnumSet.of(ColorSetEnum.RED);
        System.out.println("指定元素: " + enumSet4);
        EnumSet<ColorSetEnum> enumSet5 = EnumSet.of(ColorSetEnum.BLUE,ColorSetEnum.GREEN);
        System.out.println("指定元素: " + enumSet5);

        //复制enumSet5容器的数据作为初始化数据 --支持Collection和EnumSet
        EnumSet<ColorSetEnum> enumSet6 =  EnumSet.copyOf(enumSet5);
        System.out.println("复制EnumSet元素: " + enumSet6);

        List<ColorSetEnum> list = new ArrayList<ColorSetEnum>();
        list.add(ColorSetEnum.RED);
        list.add(ColorSetEnum.BLUE);
        list.add(ColorSetEnum.GREEN);
        list.add(ColorSetEnum.GREEN);

        EnumSet<ColorSetEnum> enumSet7 =  EnumSet.copyOf(list);
        System.out.println("复制List元素: " + enumSet7);

    }
}

enum ColorSetEnum{
    RED,BLUE,GREEN
}
