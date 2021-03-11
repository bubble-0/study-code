package com.magic.springboot.demo.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JvmDemo {
    public static void main(String[] args) {
        List<String> l = Arrays.asList("1","2","3","4","5");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i< 100000000; i ++) {
            list.add(String.valueOf(l));
        }
    }
}
