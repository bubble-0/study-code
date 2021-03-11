package com.magic.springboot.redis.controller;

import com.magic.springboot.redis.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @RequestMapping("/getuser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        System.out.println("执行了此方法,如果没有执行说明是缓存");
        return user;
    }
}
