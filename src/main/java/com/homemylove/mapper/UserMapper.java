package com.homemylove.mapper;

import com.homemylove.entities.User;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    User selectByPrimaryKey(Long userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名和密码查询
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User getUserByUsernameAndPwd(String username,String password);

    /**
     * 根据参数查询用户列表
     * @param username 用户名
     * @param mobile 手机号
     * @param isLock 状态
     * @return 用户列表
     */
    List<User> getUserListByParams(String username,String mobile,String isLock);

    /**
     * 更新用户，一部分
     * @param user 用户
     * @return 影响行数
     */
    int updateUser(User user);

    int resetPwd(Long userId, String pwd);
}