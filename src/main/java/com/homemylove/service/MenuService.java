package com.homemylove.service;

import com.homemylove.entities.Menu;
import com.homemylove.entities.vo.MenuVo;
import com.homemylove.entities.vo.NodeVo;
import org.w3c.dom.Node;

import java.util.List;

public interface MenuService {

    List<MenuVo> getAllMenu();

    List<NodeVo> getAllNode();

    boolean saveMenu(Menu menu);

    boolean deleteMenuBatch(List<Long> ids);

    Menu getMenuById(Long id);
}
