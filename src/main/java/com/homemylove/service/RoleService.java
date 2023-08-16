package com.homemylove.service;

import com.github.pagehelper.PageInfo;
import com.homemylove.entities.Role;

public interface RoleService {
    Role getRole(Long roleId);

    PageInfo<Role> getRoleList(String roleName, String roleNo, Integer page, Integer limit);

    boolean existExceptId(String roleName,String roleNo,Long roleId);

    boolean saveRole(Role role);

    boolean deleteRole(Long delId);
}
