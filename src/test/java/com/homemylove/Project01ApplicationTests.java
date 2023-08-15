package com.homemylove;

import com.homemylove.convert.UserVoConvert;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.UserVo;
import com.homemylove.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Project01ApplicationTests {

    @Resource
    UserService userService;

    @Test
    void contextLoads() {
//        List<User> list = userService.getUserList("ro", "", 1, 1, "N");
//
//        for (User user : list) {
//            UserVoConvert instance = UserVoConvert.INSTANCE;
//            UserVo userVo = instance.userToUserVo(user);
//            System.out.println(userVo);
//        }
//
//
//        System.out.println(list);

        User user = new User();
        user.setUserId(1L);
        user.setRealName("Yae Miko");

        UserVo userVo = UserVoConvert.INSTANCE.userToUserVo(user);
        System.out.println("uservo="+userVo);

    }

}
