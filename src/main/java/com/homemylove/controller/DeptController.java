package com.homemylove.controller;

import com.github.pagehelper.PageInfo;
import com.homemylove.auth.Authenticator;
import com.homemylove.convert.DeptVoConvert;
import com.homemylove.entities.Dept;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.DeptVo;
import com.homemylove.resp.Resp;
import com.homemylove.service.DeptService;
import com.homemylove.service.UserService;
import com.homemylove.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "公司管理")
@Slf4j
@RequestMapping("/api")
public class DeptController {

    @Resource
    private DeptService deptService;

    @Resource
    private UserService userService;

    @Resource
    private Authenticator authenticator;

    @PostMapping("/Dept/list")
    @ApiOperation("获取公司列表")
    public Resp deptList(@RequestParam("deptName") String deptName,
                         @RequestParam("deptNo") String deptNo,
                         @RequestParam("page") Integer page,
                         @RequestParam("limit") Integer limit){

        // 查询公司
        PageInfo<Dept> pageInfo = deptService.getDeptList(deptName, deptNo, page, limit);
        List<Dept> depts = pageInfo.getList();

        List<DeptVo> deptVos = depts.stream().map(dept -> DeptVoConvert.INSTANCE.deptToDeptVo(dept, userService.getUserNameById(dept.getEditUser()))).toList();

        HashMap<String, Object> data = new HashMap<>();
        data.put("count",pageInfo.getTotal());
        data.put("list",deptVos);

        Resp resp = new Resp();
        resp.setSuccess(true);
        resp.setCode(200);
        resp.setData(data);

        return resp;
    }


    @PostMapping("Dept/save")
    @ApiOperation("保存-编辑")
    public Resp saveDept(@RequestParam("deptId") @Nullable Long deptId,
                         @RequestParam("deptName") String deptName,
                         @RequestParam("deptNo") String deptNo,
                         @RequestParam("token") String token){
        deptName = StringUtils.trimIfNotNull(deptName);
        deptNo = StringUtils.trimIfNotNull(deptNo);
        // 结果
        boolean result;
        // 提示
        String msg;
        int code;
        // 什么情况下可以写
        // 不存在就能写
        if(!deptService.existExceptId(deptName,deptNo,deptId)){
            Dept dept = new Dept();
            // 认证
            Long edit = authenticator.auth(token).getId();
            Date time = new Date();

            if(deptId == null){
                // 新增
                dept.setAddUser(edit);
                dept.setAddTime(time);
            }
            dept.setEditUser(edit);
            dept.setEditTime(time);

            dept.setDeptId(deptId);
            dept.setDeptName(deptName);
            dept.setDeptNo(deptNo);

            result = deptService.saveDept(dept);
            msg = result ? "添加部门成功" : "添加部门失败";
            code = result ? 200 : 500;
        }else {
            // 存在了就不能写
            result = false;
            msg = "部门名称或部门代码已存在";
            code = 409;
        }

        Resp resp = new Resp();
        resp.setSuccess(result);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    @GetMapping("/Dept/delete")
    @ApiOperation("删除部门")
    public Resp deleteDept(@RequestParam("ids") Long delId){
        // todo 验证是否有资格删除

        boolean result = deptService.deleteDept(delId);
        Resp resp = new Resp();
        if(result){
            // 成功
            resp.setSuccess(true);
            resp.setCode(200);
            resp.setMsg("删除部门成功");
        }else {
            resp.setSuccess(false);
            resp.setCode(500);
            resp.setMsg("删除部门失败");
        }
        return resp;

    }

}
