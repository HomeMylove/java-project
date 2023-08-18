package com.homemylove.service;

import java.util.List;

public interface RolePermissionsService {

    /**
     * 保存权限，有就删除，没有就添加
     * @param roleId role id
     * @param permissionIds permission list
     * @param token token
     * @return 成功与否
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds,String token);
}
