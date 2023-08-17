package com.homemylove.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Permission {
    private Long permissionId;

    private String permissionName;

    private String permission;

    private Boolean layChecked;

    private Long addUser;

    private Long editUser;

    private Date addTime;

    private Date editTime;
}