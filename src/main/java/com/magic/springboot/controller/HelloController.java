package com.magic.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("Hello")
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @RequestMapping("sayHello")
    public String sayHello() {
        return "Hello Spring boot 1";
    }

}
