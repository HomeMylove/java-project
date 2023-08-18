package com.homemylove.controller;

import com.github.pagehelper.PageInfo;
import com.homemylove.convert.PermissionVoConvert;
import com.homemylove.entities.Permission;
import com.homemylove.entities.vo.PermissionVo;
import com.homemylove.resp.Resp;
import com.homemylove.service.PermissionService;
import com.homemylove.service.RolePermissionsService;
import com.homemylove.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@Api("权限管理")
@RequestMapping("/api")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @Resource
    private RolePermissionsService rolePermissionsService;

    @Resource
    private UserService userService;

    @PostMapping("/Permission/list")
    @ApiOperation("获取权限列表")
    public Resp permissionList(@RequestParam("permissionName")@Nullable String permissionName,
                               @RequestParam("permission")@Nullable String permission,
                               @RequestParam("roleId")@Nullable Long roleId,
                               @RequestParam("page") Integer page,
                               @RequestParam("limit")Integer limit){

        PageInfo<Permission> pageInfo = permissionService.queryPermission(permissionName, permission, roleId, page, limit);
        List<PermissionVo> list = pageInfo.getList().stream().map(per -> PermissionVoConvert.INSTANCE.toPermissionVo(per, userService.getUserNameById(per.getEditUser()))).toList();

        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("list",list);
        data.put("count",pageInfo.getTotal());
        resp.setData(data);

        return resp;
    }

    @PostMapping("/Permission/save")
    @ApiOperation("权限增加(编辑)")
    public Resp permissionSave(@RequestParam("permissionId") @Nullable Long permissionId,
                               @RequestParam("permissionName") String permissionName,
                               @RequestParam("permission") String permission,
                               @RequestParam("token") String token){
        boolean result = permissionService.savePermission(permissionId,permissionName,permission,token);
        Resp resp = new Resp();
        resp.setSuccess(result);
        return resp;
    }

    @PostMapping("/RolePermission/save")
    @ApiOperation("权限配置")
    public Resp rolePermissionSave(@RequestParam("roleId") Long roleId,
                                   @RequestParam("permissionIds") List<Long> permissionIds,
                                   @RequestParam("token") String token){

        boolean result = rolePermissionsService.saveRolePermission(roleId,permissionIds,token);
        Resp resp = new Resp();
        resp.setSuccess(result);
        return resp;

    }





}
