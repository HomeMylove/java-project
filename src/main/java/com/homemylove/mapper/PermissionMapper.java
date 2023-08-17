package com.homemylove.mapper;

import com.homemylove.entities.Permission;
import com.homemylove.entities.vo.PermissionVo;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long permissionId);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long permissionId);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> queryPermissions(String permissionName,String permission,Long roleId);

    int updatePermission(Permission per);
}