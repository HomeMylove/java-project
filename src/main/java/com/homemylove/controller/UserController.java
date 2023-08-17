package com.homemylove.controller;

import com.github.pagehelper.PageInfo;
import com.homemylove.auth.AuthInfo;

import com.homemylove.auth.Authenticator;
import com.homemylove.convert.UserVoConvert;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.UserVo;
import com.homemylove.resp.Resp;
import com.homemylove.service.DeptService;
import com.homemylove.service.UserService;
import com.homemylove.utils.JwtUtil;
import com.homemylove.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@Slf4j
@Api("用户管理")
@RequestMapping("/api")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private DeptService deptService;

    @Resource
    private Authenticator authenticator;

    @Value("${jwt.auth.secret}")
    private String SECRET_KEY;

    @Value("${jwt.auth.expiration-time}")
    private long EXPIRATION_TIME;

    @Value("${user.default-pwd}")
    private String DEFAULT_PWD;
    @PostMapping("/login")
    @ApiOperation("登录")
    public Resp login(String username, String password) {
        // 查询 user
        User user = userService.login(username, password);
        Resp resp = new Resp();
        if (user != null) {
            AuthInfo authInfo = new AuthInfo(user.getUserId(), user.getUserName(), user.getRealName());
            String token = JwtUtil.generateToken(authInfo,EXPIRATION_TIME,SECRET_KEY);

            HashMap<String, Object> data = new HashMap<>();

            data.put("token",token);
            data.put("user",UserVoConvert.INSTANCE.userToUserVo(user));
            resp.setSuccess(true);
            resp.setCode(200);
            resp.setData(data);
            resp.setCode(200);
        } else {
            resp.setSuccess(false);
            resp.setMsg("查无此人");
        }
        return resp;
    }

    @PostMapping("/User/list")
    @ApiOperation("获取用户列表")
    public Resp userList(@RequestParam("userName") String username,
                             @RequestParam("userMobile") String mobile,
                             @RequestParam("isLock") String isLock,
                            @RequestParam("page") int page,
                             @RequestParam("limit")int limit){
        // 查询 user
        PageInfo<User> pageInfo = userService.getUserList(username, mobile, page, limit, isLock);
        List<User> users = pageInfo.getList();

        // 查询公司，生成vo
        List<UserVo> userVos = users.stream().map(user -> UserVoConvert.INSTANCE.userToUserVo(user, deptService.getDept(user.getDeptId()))).toList();
        HashMap<String, Object> data = new HashMap<>();

        data.put("count",pageInfo.getTotal());
        data.put("list",userVos);

        // 创建 resp
        Resp resp = new Resp();
        resp.setSuccess(true);
        resp.setCode(200);
        resp.setData(data);

        return resp;
    }

    @PostMapping("/User/save")
    @ApiOperation("用户保存(编辑)")
    public Resp saveUser(@RequestParam("userId") @Nullable Long userId,
                        @RequestParam("userName") String username,
                         @RequestParam("userRealName") String realName,
                         @RequestParam("roleId")Integer roleId,
                         @RequestParam("userMobile") String mobile,
                         @RequestParam("userEmail") String email,
                         @RequestParam("userSex") String sex,
                         @RequestParam("token") String token){

        username = StringUtils.trimIfNotNull(username);
        // 结果
        boolean result;
        // 提示
        String msg;
        int code;
        // 什么情况下可以写
        if(!(userService.userNameExists(username,userId))){
            User user = new User();

            // 认证
            Long edit = authenticator.auth(token).getId();
            Date time = new Date();

            if(userId == null){
                // 新增
                user.setAddUser(edit);
                user.setAddTime(time);
                user.setIsLock("N");
            }
            user.setEditUser(edit);
            user.setEditTime(time);

            user.setUserId(userId);
            user.setUserName(username);
            user.setRealName(realName);
            user.setRoleId(roleId);
            user.setMobile(mobile);
            user.setEmail(email);
            user.setSex(sex);

            result = userService.saveUser(user);
            msg = result ? "添加用户成功" : "添加用户失败";
            code = result ? 200 : 500;
        }else {
            result = false;
            msg = "用户名已存在";
            code = 409;
        }
        Resp resp = new Resp();
        resp.setSuccess(result);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }


    @PostMapping("User/pwd")
    @ApiOperation("重置密码")
    public Resp resetPwd(@RequestParam("userId") Long userId){
        // todo 验证是否有资格修改

        boolean result = userService.resetPwd(userId, DEFAULT_PWD);
        Resp resp = new Resp();
        if(result){
            // 成功
            resp.setSuccess(true);
            resp.setCode(200);
            resp.setMsg("重制密码成功");
        }else {
            resp.setSuccess(false);
            resp.setCode(500);
            resp.setMsg("重制密码失败");
        }
        return resp;
    }

    @DeleteMapping("/User/delete")
    @ApiOperation("删除用户")
    public Resp deleteUser(@RequestParam("ids") Long delId){
        // todo 验证是否有资格删除

        boolean result = userService.deleteUser(delId);
        Resp resp = new Resp();
        if(result){
            // 成功
            resp.setSuccess(true);
            resp.setCode(200);
            resp.setMsg("删除用户成功");
        }else {
            resp.setSuccess(false);
            resp.setCode(500);
            resp.setMsg("删除用户失败");
        }
        return resp;
    }

    @GetMapping("/User/lock")
    @ApiOperation("修改状态")
    public Resp userLock(@RequestParam("userId") Long userId,
                         @RequestParam("lock") String lock){
        Resp resp = new Resp();
        resp.setSuccess(userService.changeLockType(userId,lock));
        return resp;
    }
}
