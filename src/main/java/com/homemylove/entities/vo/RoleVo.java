package com.homemylove.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class RoleVo {
    private Long roleId;

    private String systemNo;

    private String roleNo;

    private String roleName;

    private String editUser;

    private Date editTime;
}
