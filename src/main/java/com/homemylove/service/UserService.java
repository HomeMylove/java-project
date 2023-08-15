package com.homemylove.service;

import com.homemylove.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User login(String username,String password);

    List<User> getUserList(String username,String mobile,int page,int limit,String isLock);

    boolean saveUser(User user);

    boolean resetPwd(Long userId, String pwd);

    boolean deleteUser(Long delId);
}
