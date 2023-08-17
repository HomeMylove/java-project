package com.homemylove.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {
    private Long menuId;

    private String icon;

    private String menuName;

    private String hasThird;

    private String url;

    private Long pid;

    private Integer orderValue;
}