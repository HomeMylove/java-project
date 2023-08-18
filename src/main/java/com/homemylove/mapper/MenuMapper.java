package com.homemylove.mapper;

import com.homemylove.entities.Menu;
import com.homemylove.entities.vo.NodeVo;
import com.homemylove.entities.vo.RoleMenusVo;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<NodeVo> getAllNode();

    boolean deleteMenu(Long id);

}