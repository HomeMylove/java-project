package com.homemylove.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long userId;

    private String userName;

    private String password;

    @JsonProperty("userRealName")
    private String realName;

    private String sex;

    private String mobile;

    private String email;

    private String isLock;

    private Long deptId;

    private Integer roleId;

    private Long addUser;

    private Long editUser;

    private Date addTime;

    private Date editTime;

}