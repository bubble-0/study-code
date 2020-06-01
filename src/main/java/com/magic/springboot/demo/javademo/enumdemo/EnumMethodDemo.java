package com.magic.springboot.demo.javademo.enumdemo;

import java.util.Arrays;

/**
 * 继承枚举类中方法演示
 */
public class EnumMethodDemo {
    public static void main(String[] args) {


        //构建枚举数组
        Color [] colors = {Color.RED, Color.BLUE, Color.BLACK};

        //查看ordinal值
        for (Color c : colors) {
            System.out.println("Color ordinal: " + c.ordinal());
        }

        System.out.println("================================");

        //compareTo方法 --内部实际是根据ordinal()值比较的 返回 1 -1
        System.out.println("Color compareTo " + Color.RED.compareTo(Color.BLUE));
        System.out.println("Color compareTo " + Color.BLACK.compareTo(Color.BLUE));

        System.out.println("================================");

        //获取该枚举对象的Class对象引用,当然也可以通过getClass方法
        System.out.println("Color class " + Color.RED.getDeclaringClass());
        System.out.println("Color class " + Color.BLACK.getDeclaringClass());
        System.out.println("Color class " + Color.RED.getClass());
        System.out.println("Color class " + Color.BLACK.getClass());

        System.out.println("================================");

        //name
        System.out.println("Color name " + Color.RED.name());
        System.out.println("Color name " + Color.BLUE.name());
        System.out.println("Color name " + Color.BLACK.name());

        System.out.println("================================");

        //toString
        System.out.println("Color toString " + Color.RED.toString());
        System.out.println("Color toString " + Color.BLUE.toString());
        System.out.println("Color toString " + Color.BLACK.toString());

        System.out.println("================================");

        //valueOf
        Color red = Enum.valueOf(Color.class, Color.RED.name());
        Color blue = Enum.valueOf(Color.class, Color.BLUE.name());

        System.out.println(red);
        System.out.println(blue);

        System.out.println("================================");

        /**
         * values()方法和valueOf(String name)方法是编译器生成的static方法，因此从前面的分析
         * 中，在Enum类中并没出现values()方法，但valueOf()方法还是有出现的，
         * 只不过编译器生成的valueOf()方法需传递一个name参数，
         * 而Enum自带的静态方法valueOf()则需要传递两个方法，
         * 从前面反编译后的代码可以看出，编译器生成的valueOf方法最终还是调用了Enum类的valueOf方法(为了方便调用)
         */

        Color [] arrs = Color.values();
        System.out.println(Arrays.toString(arrs));
        Color redv = Color.valueOf("RED");
        System.out.println(redv);

        System.out.println("================================");

        /**
         * 当枚举实例向上转型为Enum类型后，values()方法将会失效，也就无法一次性获取所有枚举实例变量
         * 由于Class对象的存在，即使不使用values()方法，还是有可能一次获取到所有枚举实例变量的，在Class对象中存在如下方法
         * getEnumConstants()	返回该枚举类型的所有元素，如果Class对象不是枚举类型，则返回null。
         * isEnum()	当且仅当该类声明为源代码中的枚举时返回 true
         */

        //values方法
        Color [] newarr = Color.values();
        System.out.println("正常使用: " + newarr);

        //向上转型
        Enum e = Color.RED;

        //由于Enum类中不存在该方法无法使用
        //e.values();

        //使用class输出
        Class<?> clasz = e.getDeclaringClass();
        if (clasz.isEnum()) {
            Color [] classArr = (Color[]) clasz.getEnumConstants();
            System.out.println("class : " + classArr);
        }

    }
}
