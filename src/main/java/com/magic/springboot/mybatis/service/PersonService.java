package com.magic.springboot.mybatis.service;

import com.magic.springboot.mybatis.entites.Person;

import java.util.List;

public interface PersonService {

    int addPerson(Person person);

    int updatePerson(Person person);

    int deletePersonById(Person person);

    List<Person> queryPersonById(Person person);
}
