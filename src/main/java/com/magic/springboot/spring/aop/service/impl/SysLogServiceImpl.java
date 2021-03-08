package com.magic.springboot.spring.aop.service.impl;

import com.magic.springboot.spring.aop.Mapper.SysLogMapper;
import com.magic.springboot.spring.aop.entities.SysLog;
import com.magic.springboot.spring.aop.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public void saveSysLog(SysLog sysLog) {
        sysLogMapper.saveSysLog(sysLog);
    }
}
