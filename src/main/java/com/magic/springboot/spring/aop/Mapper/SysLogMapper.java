package com.magic.springboot.spring.aop.Mapper;

import com.magic.springboot.spring.aop.entities.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLogMapper {

    @Insert("insert into sys_logs(id, userid, username, time, createdate, operating) values (#{id}, #{userid}, #{username}, #{time}, #{createdate}, #{operating})")
    int saveSysLog(SysLog sysLog);
}
