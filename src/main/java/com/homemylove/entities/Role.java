package com.homemylove.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private Long roleId;

    private String systemNo;

    private String roleNo;

    private String roleName;

    private Long addUser;

    private Long editUser;

    private Date addTime;

    private Date editTime;
}