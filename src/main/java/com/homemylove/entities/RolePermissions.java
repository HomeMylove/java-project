package com.homemylove.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class RolePermissions {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Long addUser;

    private Long editUser;

    private Date addTime;

    private Date editTime;

}