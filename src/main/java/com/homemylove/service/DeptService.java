package com.homemylove.service;

import com.github.pagehelper.PageInfo;
import com.homemylove.entities.Dept;
import com.homemylove.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

public interface DeptService {
    Dept getDept(Long deptId);

    PageInfo<Dept> getDeptList(String deptName, String deptNo, Integer page, Integer limit);

    boolean deptExists(String deptName,String deptNo);

    boolean saveDept(Dept dept);

    boolean deleteDept(Long delId);
}
