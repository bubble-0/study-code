package com.test;

import com.magic.springboot.mybatis.entites.Person;
import com.magic.springboot.mybatis.mappers.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper {
    @Autowired
    PersonMapper personMapper;

    @Test
    public void test() {
        Person p = new Person();
        personMapper.add(p);
    }


}
