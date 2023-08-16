package com.homemylove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homemylove.entities.Role;
import com.homemylove.mapper.RoleMapper;
import com.homemylove.service.RoleService;
import com.homemylove.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper  roleMapper;

    @Override
    public Role getRole(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public PageInfo<Role> getRoleList(String roleName, String roleNo, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        // 参数调整
        List<Role> list = roleMapper.getRoleListByParams(roleName, roleNo);

        return new PageInfo<>(list);
    }

    /**
     * 部门名称或代码是否存在,除了给的id以外
     * @param roleName 角色名称
     * @param roleNo 角色代码
     * @param roleId 角色id
     * @return 是否存在
     */
    @Override
    public boolean existExceptId(String roleName, String roleNo,Long roleId) {
        return roleMapper.hasRole(roleName,roleNo,roleId) > 0;
    }

    @Override
    public boolean saveRole(Role role) {
        if(role.getRoleId() == null){
            // 空的 新增操作
            return roleMapper.insert(role) > 0;
        }else {
            // 编辑
            return roleMapper.updateRole(role) > 0;
        }
    }

    @Override
    public boolean deleteRole(Long delId) {
        return roleMapper.deleteByPrimaryKey(delId) > 0;
    }
}
