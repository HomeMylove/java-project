package com.homemylove.service;

import com.github.pagehelper.PageInfo;
import com.homemylove.entities.User;

import java.util.List;

public interface UserService {
    User login(String username,String password);

    PageInfo<User> getUserList(String username, String mobile, int page, int limit, String isLock);

    User getUserNameById(Long userId);

    boolean saveUser(User user);

    boolean resetPwd(Long userId, String pwd);

    boolean deleteUser(Long delId);

    boolean userNameExists(String userName,Long userId);
}
