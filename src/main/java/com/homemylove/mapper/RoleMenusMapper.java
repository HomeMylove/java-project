package com.homemylove.mapper;

import com.homemylove.entities.RoleMenus;
import java.util.List;

public interface RoleMenusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleMenus record);

    RoleMenus selectByPrimaryKey(Long id);

    List<RoleMenus> selectAll();

    int updateByPrimaryKey(RoleMenus record);

    List<RoleMenus> getRoleMenusByRoleId(Long id);

    void deleteAllRightByRoleId(Long roleId);

}