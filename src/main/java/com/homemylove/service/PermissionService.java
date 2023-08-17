package com.homemylove.service;

import com.github.pagehelper.PageInfo;
import com.homemylove.entities.Permission;
import com.homemylove.entities.vo.PermissionVo;
import io.swagger.models.auth.In;

import java.util.List;

public interface PermissionService {

    PageInfo<Permission> queryPermission(String permissionName, String permission, Long roleId, Integer page, Integer limit);

    boolean savePermission(Long permissionId, String permissionName, String permission, String token);
}
