package com.homemylove.mapper;

import com.homemylove.entities.Dept;
import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(Dept record);

    Dept selectByPrimaryKey(Long deptId);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept record);

    List<Dept> getDeptListByParams(String deptName,String deptNo);

    int hasDept(String deptName, String deptNo,Long deptId);

    int updateDept(Dept dept);
}