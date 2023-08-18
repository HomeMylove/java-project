package com.homemylove.service.impl;

import com.homemylove.auth.AuthInfo;
import com.homemylove.auth.Authenticator;
import com.homemylove.entities.RolePermissions;
import com.homemylove.mapper.RolePermissionsMapper;
import com.homemylove.service.RolePermissionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionsServiceImpl implements RolePermissionsService {

    @Resource
    private RolePermissionsMapper rolePermissionsMapper;

    @Resource
    private Authenticator authenticator;

    @Override
    public boolean saveRolePermission(Long roleId, List<Long> permissionIds,String token) {
        int count = 1;
        for (Long permissionId : permissionIds) {
            Long id = rolePermissionsMapper.hasRolePermission(roleId,permissionId);
            if(id != null){
                // 删除
                count *= rolePermissionsMapper.deleteByPrimaryKey(id);
            }else {
                Long userId = authenticator.auth(token).getId();
                Date time = new Date();

                RolePermissions permissions = new RolePermissions();
                permissions.setRoleId(roleId);
                permissions.setPermissionId(permissionId);
                permissions.setAddUser(userId);
                permissions.setAddTime(time);

                count *= rolePermissionsMapper.insert(permissions);
            }
        }
        return count == 1;
    }
}
