package com.homemylove.service.impl;

import com.homemylove.convert.MenuVoConvert;
import com.homemylove.entities.Menu;
import com.homemylove.entities.vo.MenuVo;
import com.homemylove.entities.vo.NodeVo;
import com.homemylove.mapper.MenuMapper;
import com.homemylove.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> getAllMenu() {
        List<Menu> menus = menuMapper.selectAll();
        // 获取MenuVos
        // 先取出父级菜单
        List<MenuVo> menuVos = menus.stream().filter(menu -> menu.getPid() == null).toList()
                .stream().map(MenuVoConvert.INSTANCE::menuToMenuVo).toList();

        menus.forEach(menu -> {
            Long pid = menu.getPid();
            if(pid != null){
                for (MenuVo menuVo : menuVos) {
                    if(Objects.equals(menuVo.getMenuid(), pid)){
                        if(menuVo.getMenus() == null){
                            menuVo.setMenus(new ArrayList<>());
                        }
                        menuVo.getMenus().add(MenuVoConvert.INSTANCE.menuToMenuVo(menu));
                    }
                }
            }
        });
        return menuVos;
    }

    @Override
    public List<NodeVo> getAllNode() {
        return menuMapper.getAllNode();
    }

    @Override
    public boolean saveMenu(Menu menu) {
        if(menu.getMenuId()== null){
            return menuMapper.insert(menu) > 0;
        }else {
            return menuMapper.updateByPrimaryKey(menu) > 0;
        }
    }

    @Override
    public boolean deleteMenuBatch(List<Long> ids) {
        int count = 0;
        for (Long id : ids) {
            count +=  menuMapper.deleteMenu(id) ? 0 : 1;
        }
        return count == 0;
    }

    @Override
    public Menu getMenuById(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }
}
