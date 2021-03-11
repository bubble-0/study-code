package com.magic.springboot.demo.jdk8.generic;

public class Generic<T> {
    private T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    ///Number类型的形参方法
//    public static Number showThisKey(Generic<Number> obj) {
//        return obj.getKey();
//    }

    //修改
    public static Object showThisKey(Generic<?> obj) {
        return obj.getKey();
    }

    public static void main(String[] args) {
        Generic<Number> number = new Generic<Number>();

        Generic<Integer> integer = new Generic<Integer>();

        System.out.println(showThisKey(number));

        System.out.println(showThisKey(integer));

        /**
         * 通过提示信息我们可以看到Generic<Integer>不能被看作为Generic<Number>的子类。
         * 由此可以看出:同一种泛型可以对应多个版本（因为参数类型是不确定的），
         * 不同版本的泛型类实例是不兼容的。
         * 此时就要用到泛型通配符?
         */
    }
}
