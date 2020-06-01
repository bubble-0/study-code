package com.magic.springboot.demo.javademo.enumdemo;

/**
 * 由于Java单继承的原因，enum类并不能再继承其它类，但并不妨碍它实现接口，
 * 因此enum类同样是可以实现多接口的
 */
public enum InterfaceEnum implements Eat, Sport{
    RIBBIT(0,"兔子"), DOG(1, "狗");

    private String desc;

    private int id;

    InterfaceEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }


    @Override
    public String eat() {
        return " eat ";
    }

    @Override
    public String run() {
        return " run ";
    }

    public String getDesc() {
        return desc;
    }
}

interface Eat{
    String eat();
}

interface Sport{
    String run();
}

class InterfaceDemoMain{
    public static void main(String[] args) {
        for (InterfaceEnum e: InterfaceEnum.values()) {
            System.out.println("枚举项： " + e.getDesc() + e.eat());
            System.out.println("枚举项： " + e.getDesc() + e.run());

        }
    }
}