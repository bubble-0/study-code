package com.magic.springboot.mybatis.mappers;

import com.magic.springboot.mybatis.entites.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 基于注解的Mapper
 */
@Mapper
public interface PersonMapper {
    @Insert("insert into TEST_TABLE(id,name,age) values (#{id},#{name}, #{age})")
    int add(Person person);

    @Update("update TEST_TABLE set name = #{name}, age = #{age} where id = #{id}")
    int update(Person person);

    @Delete("delete from TEST_TABLE where id = #{id}")
    int deleteById(Person person);

    @Select("select * from TEST_TABLE where id = #{id}")
    @Results(id = "person", value = {
            @Result(property = "id", column = "id", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "age", column = "age", javaType = Integer.class)
    })
    List<Person> queryPersonById(Person person);

}
