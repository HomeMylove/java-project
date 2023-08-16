package com.homemylove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homemylove.entities.Dept;
import com.homemylove.mapper.DeptMapper;
import com.homemylove.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper  deptMapper;

    @Override
    public Dept getDept(Long deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public PageInfo<Dept> getDeptList(String deptName, String deptNo, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        // 参数调整
        deptName = deptName == null ? null : deptName.trim();
        deptNo = deptNo == null ? null : deptNo.trim();
        List<Dept> list = deptMapper.getDeptListByParams(deptName, deptNo);

        return new PageInfo<>(list);
    }

    /**
     * 部门名称或代码是否存在
     * @param deptName 部门名称
     * @param deptNo 部门代码
     * @return 是否存在
     */
    @Override
    public boolean deptExists(String deptName, String deptNo) {
        return deptMapper.hasDept(deptName,deptNo) > 0;
    }

    @Override
    public boolean saveDept(Dept dept) {
        if(dept.getDeptId() == null){
            // 空的 新增操作
            return deptMapper.insert(dept) > 0;
        }else {
            // 编辑
            return deptMapper.updateDept(dept) > 0;
        }
    }

    @Override
    public boolean deleteDept(Long delId) {
        return deptMapper.deleteByPrimaryKey(delId) > 0;
    }
}
