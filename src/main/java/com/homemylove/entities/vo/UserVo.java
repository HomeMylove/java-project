package com.homemylove.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVo {
    private Long userId;

    private String userName;

    private String userRealName;

    private String userSex;

    private String userMobile;

    private String userEmail;

    private String isLock;

    private Long deptId;

    private String deptName;

    private Integer roleId;

    private Long addUser;

    private Long editUser;

    private Date addTime;

    private Date editTime;

}
