package com.homemylove.mapper;

import com.homemylove.entities.RolePermissions;
import java.util.List;

public interface RolePermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermissions record);

    RolePermissions selectByPrimaryKey(Long id);

    List<RolePermissions> selectAll();

    int updateByPrimaryKey(RolePermissions record);
}