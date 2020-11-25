package com.magic.springboot.mybatis.service.imp;

import com.magic.springboot.mybatis.entites.Person;
import com.magic.springboot.mybatis.mappers.PersonMapper;
import com.magic.springboot.mybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {
    @Autowired
    PersonMapper personMapper;

    @Override
    public int addPerson(Person person) {
        return this.personMapper.add(person);
    }

    @Override
    public int updatePerson(Person person) {
        return this.personMapper.update(person);
    }

    @Override
    public int deletePersonById(Person person) {
        return this.personMapper.deleteById(person);
    }

    @Override
    public List<Person> queryPersonById(Person person) {
        return this.personMapper.queryPersonById(person);
    }
}
