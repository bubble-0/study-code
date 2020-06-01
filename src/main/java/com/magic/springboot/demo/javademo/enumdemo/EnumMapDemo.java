package com.magic.springboot.demo.javademo.enumdemo;

import java.util.*;

public class EnumMapDemo {
    public static void main(String[] args) {
        /**
         * 先思考这样一个问题，现在我们有一堆size大小相同而颜色不同的数据，
         * 需要统计出每种颜色的数量是多少以便将数据录入仓库，定义如下枚举用于表示颜色Color
         */
        List<ColorEntity> list = new ArrayList<ColorEntity>();
        list.add(new ColorEntity("C001",ColorMap.RED));
        list.add(new ColorEntity("C002",ColorMap.RED));
        list.add(new ColorEntity("C003",ColorMap.RED));
        list.add(new ColorEntity("C004",ColorMap.BLUE));
        list.add(new ColorEntity("C005",ColorMap.BLUE));
        list.add(new ColorEntity("C006",ColorMap.GREEN));
        list.add(new ColorEntity("C007",ColorMap.GREEN));
        list.add(new ColorEntity("C008",ColorMap.GREEN));

        //方案1使用hashmap
        Map<ColorMap, Integer> map = new HashMap<>();
        for (ColorEntity c : list) {
            ColorMap cm = c.getColor();
            if (map.containsKey(cm)) {
                map.put(cm, map.get(cm) + 1);
            } else {
                map.put(cm, 1);
            }
        }

        System.out.println(map.toString());
        System.out.println("=========================");

        //方案2使用EnumMap --传入枚举的类对象
        Map<ColorMap, Integer> enumMap = new EnumMap<ColorMap, Integer>(ColorMap.class);
        for (ColorEntity c : list) {
            ColorMap cm = c.getColor();
            if (enumMap.containsKey(cm)) {
                enumMap.put(cm, enumMap.get(cm) + 1);
            } else {
                enumMap.put(cm, 1);
            }
        }

        System.out.println(enumMap);

        /**
         * EnumMap作为枚举的专属的集合，我们没有理由再去使用HashMap，
         * 毕竟EnumMap要求其Key必须为Enum类型，因而使用Color枚举实例作为key是最恰当不过了，
         * 也避免了获取name的步骤，更重要的是EnumMap效率更高，因为其内部是通过数组实现的
         * EnumMap不允许key为null
         */



    }
}

enum ColorMap{
    RED,BLUE,GREEN
}

class ColorEntity{
    private String id;

    private ColorMap color;

    public ColorEntity(String id, ColorMap color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public ColorMap getColor() {
        return color;
    }
}