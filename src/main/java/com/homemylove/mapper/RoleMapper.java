package com.homemylove.mapper;

import com.homemylove.entities.Role;
import com.homemylove.entities.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Long roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> getRoleListByParams(String roleName, String roleNo);

    int hasRole(String roleName, String roleNo,Long roleId);

    int updateRole(Role role);
}