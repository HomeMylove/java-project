package com.homemylove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homemylove.auth.AuthInfo;
import com.homemylove.auth.Authenticator;
import com.homemylove.convert.PermissionVoConvert;
import com.homemylove.entities.Permission;
import com.homemylove.entities.vo.PermissionVo;
import com.homemylove.mapper.PermissionMapper;
import com.homemylove.service.PermissionService;
import com.homemylove.service.UserService;
import com.homemylove.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private Authenticator authenticator;

    @Resource
    private UserService userService;

    @Override
    public PageInfo<Permission> queryPermission(String permissionName, String permission, Long roleId, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        permissionName = StringUtils.trimIfNotNull(permissionName);
        permission = StringUtils.trimIfNotNull(permission);

        List<Permission> permissions = permissionMapper.queryPermissions(permissionName, permission, roleId);
        return new PageInfo<>(permissions);
    }

    @Override
    public boolean savePermission(Long permissionId, String permissionName, String permission, String token) {
        permissionName = StringUtils.trimIfNotNull(permissionName);
        permission = StringUtils.trimIfNotNull(permission);

        AuthInfo auth = authenticator.auth(token);
        Long userId = auth.getId();
        Date time = new Date();

        Permission per = new Permission();

        per.setPermissionId(permissionId);
        per.setPermissionName(permissionName);
        per.setPermission(permission);

        per.setEditUser(userId);
        per.setEditTime(time);

        if(permissionId != null){
            return permissionMapper.updatePermission(per) > 0;
        }else {
            // 新增
            per.setAddUser(userId);
            per.setAddTime(time);

            return permissionMapper.insert(per) > 0;
        }

    }
}
