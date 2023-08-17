package com.homemylove.entities.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class PermissionVo {
    private Long permissionId;

    private String permissionName;

    private String permission;

    @JsonProperty("LAY_CHECKED")
    private Boolean layChecked;

    private String editUser;

    private Date editTime;
}
