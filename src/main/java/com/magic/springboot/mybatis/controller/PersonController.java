package com.magic.springboot.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.magic.springboot.aop.annotation.Log;
import com.magic.springboot.common.context.Result;
import com.magic.springboot.mybatis.entites.Person;
import com.magic.springboot.mybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Log("添加用户")
    @PostMapping("addPerson")
    public JSONObject addPerson(Person person) {
        JSONObject jsonObject = new JSONObject();
        try {
            personService.addPerson(person);
            jsonObject.put(Result.RESULT, Result.SUCC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put(Result.RESULT, Result.SUCC);
        return jsonObject;
    }

    @PostMapping("updatePerson")
    public JSONObject updatePerson(Person person) {
        JSONObject jsonObject = new JSONObject();
        try {
            personService.updatePerson(person);
            jsonObject.put(Result.RESULT, Result.SUCC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put(Result.RESULT, Result.SUCC);
        return jsonObject;
    }

    @PostMapping("deletePersonById")
    public JSONObject deletePersonById(Person person) {
        JSONObject jsonObject = new JSONObject();
        try {
            personService.deletePersonById(person);
            jsonObject.put(Result.RESULT, Result.SUCC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put(Result.RESULT, Result.SUCC);
        return jsonObject;
    }

    @GetMapping("queryPersonById")
    public List<Person> queryPersonById(Person person) {
        return personService.queryPersonById(person);
    }


}

