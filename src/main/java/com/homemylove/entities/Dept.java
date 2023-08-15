package com.homemylove.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dept {
    private Long deptId;

    private String deptName;

    private String deptNo;

    private Long parentId;

    private Long editUser;

    private Long addUser;

    private Date editTime;

    private Date addTime;
}