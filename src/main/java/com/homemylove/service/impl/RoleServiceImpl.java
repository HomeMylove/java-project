package com.homemylove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homemylove.convert.RoleMenusConvert;
import com.homemylove.convert.RoleVoConvert;
import com.homemylove.entities.Menu;
import com.homemylove.entities.Role;
import com.homemylove.entities.RoleMenus;
import com.homemylove.entities.vo.RoleDropDownVo;
import com.homemylove.entities.vo.RoleMenusVo;
import com.homemylove.mapper.MenuMapper;
import com.homemylove.mapper.RoleMapper;
import com.homemylove.mapper.RoleMenusMapper;
import com.homemylove.service.RoleService;
import com.homemylove.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper  roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenusMapper roleMenusMapper;

    @Override
    public Role getRole(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public PageInfo<Role> getRoleList(String roleName, String roleNo, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        // 参数调整
        List<Role> list = roleMapper.getRoleListByParams(roleName, roleNo);

        return new PageInfo<>(list);
    }

    /**
     * 部门名称或代码是否存在,除了给的id以外
     * @param roleName 角色名称
     * @param roleNo 角色代码
     * @param roleId 角色id
     * @return 是否存在
     */
    @Override
    public boolean existExceptId(String roleName, String roleNo,Long roleId) {
        return roleMapper.hasRole(roleName,roleNo,roleId) > 0;
    }

    @Override
    public boolean saveRole(Role role) {
        if(role.getRoleId() == null){
            // 空的 新增操作
            return roleMapper.insert(role) > 0;
        }else {
            // 编辑
            return roleMapper.updateRole(role) > 0;
        }
    }

    @Override
    public boolean deleteRole(Long delId) {
        return roleMapper.deleteByPrimaryKey(delId) > 0;
    }

    @Override
    public List<RoleDropDownVo> getRoleDropDownVoList() {
        return roleMapper.getRoleDropDownVoList();
    }

    @Override
    public List<RoleMenusVo> getRoleMenus(Long id) {

        List<Menu> menus = menuMapper.selectAll();
        List<RoleMenus> roleMenus = roleMenusMapper.getRoleMenusByRoleId(id);

        // 过滤出父级
        List<RoleMenusVo> roleMenusVos = menus.stream().filter(menu -> menu.getPid() == null).toList()
                .stream().map(menu -> {
                    RoleMenusVo roleMenusVo = RoleMenusConvert.INSTANCE.toRoleMenusVo(menu);
                    for (RoleMenus roleMenu : roleMenus) {
                        if (Objects.equals(roleMenu.getMenuId(), roleMenusVo.getId())) {
                            roleMenusVo.setChecked(true);
                            break;
                        }
                    }
                    return roleMenusVo;
                }).toList();

        menus.stream().filter(menu -> menu.getPid() != null).toList()
                .stream().forEach(menu -> {
                    RoleMenusVo roleMenusVo = RoleMenusConvert.INSTANCE.toRoleMenusVo(menu);
                    for (RoleMenus roleMenu : roleMenus) {
                        if (Objects.equals(roleMenu.getMenuId(), roleMenusVo.getId())) {
                            roleMenusVo.setChecked(true);
                            break;
                        }
                    }
                    Long pid = menu.getPid();
                    for (RoleMenusVo menusVo : roleMenusVos) {
                        if(Objects.equals(menusVo.getId(), pid)){
                            if(menusVo.getChildren()==null) menusVo.setChildren(new ArrayList<>());
                            menusVo.getChildren().add(roleMenusVo);
                        }
                    }
                });
        return roleMenusVos;
    }

    @Override
    public boolean roleRightSave(Long roleId, List<Long> moduleIds) {
        roleMenusMapper.deleteAllRightByRoleId(roleId);

        int count = 1;
        for (Long moduleId : moduleIds) {
            RoleMenus menus = new RoleMenus();
            menus.setRoleId(roleId);
            menus.setMenuId(moduleId);
            count *= roleMenusMapper.insert(menus);
        }
        return count == 1;
    }
}
