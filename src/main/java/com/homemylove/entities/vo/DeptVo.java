package com.homemylove.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeptVo {
    private Long deptId;

    private String deptName;

    private String deptNo;

    private String editUser;

    private Date editTime;

}
