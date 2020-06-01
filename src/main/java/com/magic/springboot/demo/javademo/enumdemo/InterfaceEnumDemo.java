package com.magic.springboot.demo.javademo.enumdemo;

/**
 * 因为枚举类默认继承了Enum类，所以想多态创建多个枚举就需要用到接口
 * 有时候，我们可能需要对一组数据进行分类，
 * 比如进行食物菜单分类而且希望这些菜单都属于food类型，appetizer(开胃菜)、mainCourse(主菜)、dessert(点心)、Coffee等
 * 每个菜单下面是具体的菜名
 */
public interface InterfaceEnumDemo {
    enum Appetizer implements InterfaceEnumDemo{
        SALAD, SOUP
    }

    enum MainCourse implements InterfaceEnumDemo{
        LASAGNE, BURRITO
    }

    enum Coffee implements InterfaceEnumDemo{
        BLACK_COFFEE, TEA
    }
}

class InterfaceEnumDemoMain {
    public static void main(String[] args) {
        InterfaceEnumDemo e = InterfaceEnumDemo.Appetizer.SALAD;
        e = InterfaceEnumDemo.MainCourse.LASAGNE;
        e = InterfaceEnumDemo.Coffee.BLACK_COFFEE;
    }
}
