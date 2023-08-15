package com.homemylove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homemylove.entities.User;
import com.homemylove.mapper.UserMapper;
import com.homemylove.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User login(String username,String password){
        return userMapper.getUserByUsernameAndPwd(username,password);
    }

    @Override
    public List<User> getUserList(String username, String mobile, int page, int limit, String isLock) {
        PageHelper.startPage(page,limit);
        // 检查参数
        if(username != null) username = username.trim();
        if(mobile != null) mobile = mobile.trim();
        if(isLock != null && (!isLock.equals("Y") && !isLock.equals("N"))) isLock = null;
        List<User> list = userMapper.getUserListByParams(username, mobile, isLock);
        PageInfo<User> pageInfo = new PageInfo<>(list);

        return pageInfo.getList();
    }

    @Override
    public boolean saveUser(User user) {
        if(user.getUserId() == null){
            // 没有 id ，新增
        return userMapper.insert(user) > 0;
        }else {
            // 有 id ，修改
            return userMapper.updateUser(user) > 0;
        }

    }

    @Override
    public boolean resetPwd(Long userId, String pwd) {
        return userMapper.resetPwd(userId,pwd) > 0;
    }

    @Override
    public boolean deleteUser(Long delId) {
        return userMapper.deleteByPrimaryKey(delId) > 0;
    }


}
