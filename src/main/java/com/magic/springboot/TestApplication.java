package com.magic.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 引导类，spring boot的 入门类
 */

//@EnableAutoConfiguration  启动自动配置,启动一次就可以了
//@ComponentScan 开启自动扫描，只扫描该类所在的包和子包
@SpringBootApplication //组合组件，相当于@EnableAutoConfigtiration  @ComponentScan  @SpringBootConfiguration的组合
@EnableScheduling
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);}

}
