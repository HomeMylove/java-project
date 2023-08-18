package com.homemylove.entities.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RoleMenusVo {
    private Long id;
    private String name;
    private boolean checked;
    private List<RoleMenusVo> children;
}
