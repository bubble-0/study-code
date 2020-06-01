package com.magic.springboot.demo.javademo.enumdemo;

/**
 * 与常规抽象类一样，enum类允许我们为其定义抽象方法，
 * 然后使每个枚举实例都实现该方法，以便产生不同的行为方式
 */
public enum AbstactEnum {
    RUN{
        @Override
        public void AbstactMethod() {
            System.out.println("跑步");
        }
    },WALK{
        @Override
        public void AbstactMethod() {
            System.out.println("步行");
        }
    };

    //普通方法
    public String normalMethod() {
        return "this is normal Method";
    }

    //抽象方法，需要各自枚举项去实现自己的行为
    public abstract void AbstactMethod();

}

class DemoMain{
    public static void main(String[] args) {
        AbstactEnum run = AbstactEnum.RUN;
        AbstactEnum walk = AbstactEnum.WALK;

        System.out.println("run普通方法: " + run.normalMethod());
        System.out.println("walk普通方法: " + walk.normalMethod());

        run.AbstactMethod();
        walk.AbstactMethod();
    }
}
