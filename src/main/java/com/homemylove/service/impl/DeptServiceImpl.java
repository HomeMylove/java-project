package com.homemylove.service.impl;

import com.homemylove.entities.Dept;
import com.homemylove.mapper.DeptMapper;
import com.homemylove.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper  deptMapper;

    @Override
    public Dept getDept(Long deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }
}
