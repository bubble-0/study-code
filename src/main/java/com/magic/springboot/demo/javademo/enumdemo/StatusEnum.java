package com.magic.springboot.demo.javademo.enumdemo;

/**
 * 自定义变量与构造函数
 * 在enum类中确实可以像定义常规类一样声明变量或者成员方法。
 * 但是我们必须注意到，如果打算在enum类中定义方法，务必在声明完枚举实例后使用分号分开，
 * 倘若在枚举实例前定义任何方法，编译器都将会报错，无法编译通过，同时即使自定义了构造函数且enum的定义结束，
 * 我们也永远无法手动调用构造函数创建枚举实例，毕竟这事只能由编译器执行。
 */
public enum StatusEnum {
    OK(1,"成功"),
    FAIL(2,"失败"),
    INIT(3,"初始化");

    private int code;

    private String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    //覆盖toString方法
    @Override
    public String toString() {
        return this.getDesc();
    }
}

class Demo {
    public static void main(String[] args) {
        //查看枚举项  没有重写toString方法之前直接返回枚举项OK,FAIL,INIT,重写之后返回desc
        for(StatusEnum s : StatusEnum.values()) {
            System.out.println(s);
        }

        //获取枚举项属性
        System.out.println("StatusEnum OK CODE" + StatusEnum.OK.getCode());
        System.out.println("StatusEnum INIT DESC" + StatusEnum.INIT.getDesc());
    }
}



