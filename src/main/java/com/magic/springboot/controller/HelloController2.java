package com.magic.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Hello2")
public class HelloController2 {

    @RequestMapping("sayHello")
    public String sayHello() {
        return "Hello Spring boot 2";
    }


}
