package com.magic.springboot.demo.other;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalcuteDemo {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");
        System.out.println("1+2的和 ："+js.eval("1+2"));
        System.out.println("1-2的差  ："+js.eval("1-2"));
        System.out.println("1*2的积  ："+js.eval("1*2"));
        System.out.println("1/2的商  ："+js.eval("1/2"));
        System.out.println("1%2的余："+js.eval("1%2"));
    }
}
