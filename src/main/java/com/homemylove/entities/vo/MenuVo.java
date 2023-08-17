package com.homemylove.entities.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuVo {
    private Long menuid;
    private String icon;
    private String menuname;
    private String hasThird;
    private String url;
//    @JsonIgnore
    private Long pid;
    private List<MenuVo> menus;
}
