package com.homemylove.service;

import com.github.pagehelper.PageInfo;
import com.homemylove.entities.Dept;
import com.homemylove.entities.vo.DeptDropDownVo;
import com.homemylove.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

public interface DeptService {
    Dept getDept(Long deptId);

    PageInfo<Dept> getDeptList(String deptName, String deptNo, Integer page, Integer limit);

    boolean existExceptId(String deptName,String deptNo,Long deptId);

    boolean saveDept(Dept dept);

    boolean deleteDept(Long delId);

    List<DeptDropDownVo> getDeptDropDown();
}
