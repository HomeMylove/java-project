package com.homemylove.controller;

import com.github.pagehelper.PageInfo;
import com.homemylove.auth.Authenticator;
import com.homemylove.convert.RoleVoConvert;
import com.homemylove.entities.Role;
import com.homemylove.entities.vo.MenuVo;
import com.homemylove.entities.vo.RoleDropDownVo;
import com.homemylove.entities.vo.RoleMenusVo;
import com.homemylove.entities.vo.RoleVo;
import com.homemylove.resp.Resp;
import com.homemylove.service.MenuService;
import com.homemylove.service.RoleService;
import com.homemylove.service.UserService;
import com.homemylove.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "角色管理")
@Slf4j
@RequestMapping("/api")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private Authenticator authenticator;

    @PostMapping("/Role/list")
    @ApiOperation("获取角色列表")
    public Resp roleList(@RequestParam("roleName") String roleName,
                         @RequestParam("roleNo") String roleNo,
                         @RequestParam("page") Integer page,
                         @RequestParam("limit") Integer limit){
        roleName = StringUtils.trimIfNotNull(roleName);
        roleNo = StringUtils.trimIfNotNull(roleNo);

        // 查询角色
        PageInfo<Role> pageInfo = roleService.getRoleList(roleName, roleNo, page, limit);
        List<Role> roles = pageInfo.getList();

        List<RoleVo> roleVos = roles.stream().map(role -> RoleVoConvert.INSTANCE.roleToRoleVo(role, userService.getUserNameById(role.getEditUser()))).toList();

        HashMap<String, Object> data = new HashMap<>();
        data.put("count",pageInfo.getTotal());
        data.put("list",roleVos);

        Resp resp = new Resp();
        resp.setSuccess(true);
        resp.setCode(200);
        resp.setData(data);

        return resp;
    }


    @PostMapping("/Role/save")
    @ApiOperation("保存-编辑")
    public Resp saveRole(@RequestParam("roleId") @Nullable Long roleId,
                         @RequestParam("roleName") String roleName,
                         @RequestParam("roleNo") String roleNo,
                         @RequestParam("systemNo") String systemNo,
                         @RequestParam("token") String token){
        roleName = StringUtils.trimIfNotNull(roleName);
        roleNo = StringUtils.trimIfNotNull(roleNo);
        // 结果
        boolean result;
        // 提示
        String msg;
        int code;
        // 什么情况下可以写
        if(!(roleService.existExceptId(roleName,roleNo,roleId))){
            Role role = new Role();

            // 认证
            Long edit = authenticator.auth(token).getId();
            Date time = new Date();

            if(roleId == null){
                // 新增
                role.setAddUser(edit);
                role.setAddTime(time);
            }
            role.setEditUser(edit);
            role.setEditTime(time);

            role.setRoleId(roleId);
            role.setRoleName(roleName);
            role.setRoleNo(roleNo);
            role.setSystemNo(systemNo);

            result = roleService.saveRole(role);
            msg = result ? "添加角色成功" : "添加角色失败";
            code = result ? 200 : 500;
        }else {
            result = false;
            msg = "角色名称或角色代码已存在";
            code = 409;
        }

        Resp resp = new Resp();
        resp.setSuccess(result);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    @DeleteMapping("/Role/delete")
    @ApiOperation("删除角色")
    public Resp deleteRole(@RequestParam("ids") Long delId){
        // todo 验证是否有资格删除

        boolean result = roleService.deleteRole(delId);
        Resp resp = new Resp();
        if(result){
            // 成功
            resp.setSuccess(true);
            resp.setCode(200);
            resp.setMsg("删除角色成功");
        }else {
            resp.setSuccess(false);
            resp.setCode(500);
            resp.setMsg("删除角色失败");
        }
        return resp;

    }

    @GetMapping("/RoleRight/tree/{id}")
    @ApiOperation("菜单权限(获取)")
    public Resp roleRightTree(@PathVariable("id") Long id){

        List<RoleMenusVo> roleMenusVos = roleService.getRoleMenus(id);

        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("list",roleMenusVos);

        resp.setData(data);
        return resp;
    }

    @GetMapping("/Role/dropDown/all")
    @ApiOperation("获取角色")
    public Resp roleDropDownAll(){
        List<RoleDropDownVo> roleDropDownVos = roleService.getRoleDropDownVoList();
        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("list",roleDropDownVos);
        resp.setData(data);
        return resp;
    }

    @PostMapping("/RoleRight/save")
    @ApiOperation("菜单权限(保存)")
    public Resp roleRightSave(@RequestParam("roleId") Long roleId,
                              @RequestParam("moduleIds") List<Long> moduleIds){

        boolean result = roleService.roleRightSave(roleId,moduleIds);

        Resp resp = new Resp();
        resp.setSuccess(result);
        return resp;
    }

}
