package com.magic.springboot.swagger.controller;

import com.magic.springboot.swagger.entitys.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户信息")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiIgnore
    @GetMapping("sayHello")
    public String sayHello() {
        return "hello";
    }

    @ApiOperation("根据id获取用户信息")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "path")
    public User getUserInfoById(@PathVariable(value = "id") Integer id) {
        User user = new User();
        user.setId("1");
        user.setName("test");
        return user;
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("test");

        User user2 = new User();
        user2.setId("2");
        user2.setName("test2");

        userList.add(user);
        userList.add(user2);
        return userList;
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User", paramType = "body")
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "succ");
        return map;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "body")
    public Map<String, Object> delUser(@PathVariable(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "succ");
        return map;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", required = true, paramType = "body"),
            @ApiImplicitParam(name = "user", value = "用户信息", dataType = "User", required = true, paramType = "body"),
    })
    @ApiOperation("修改用户信息")
    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable(value = "id") Integer id, @RequestBody  User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "succ");
        return map;
    }
}
