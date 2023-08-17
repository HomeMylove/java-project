package com.homemylove.controller;

import com.homemylove.convert.MenuVoConvert;
import com.homemylove.entities.Menu;
import com.homemylove.entities.vo.MenuVo;
import com.homemylove.mapper.MenuMapper;
import com.homemylove.resp.Resp;
import com.homemylove.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@Api("菜单管理")
@RequestMapping("/api")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/menu")
    @ApiOperation("获取用户菜单")
    public Resp getMenu(){
        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("menu",menuService.getAllMenu());
        resp.setData(data);

        return resp;
    }

    @PostMapping("/Module/list")
    @ApiOperation("获取菜单")
    public Resp moduleList(){
        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("menu",menuService.getAllMenu());
        resp.setData(data);
        return resp;
    }

    @PostMapping("/Module/nodes")
    @ApiOperation("获取父级菜单")
    public Resp moduleNodes(){
        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("node",menuService.getAllNode());
        resp.setData(data);
        return resp;
    }

    @PostMapping("/Module/save")
    @ApiOperation("修改菜单")
    public Resp moduleSave(
            @RequestParam("moduleId") @Nullable Long menuId,
            @RequestParam("parentId") Long pid,
            @RequestParam("moduleIcon") String icon,
            @RequestParam("moduleOrder")Integer order,
            @RequestParam("moduleName") String name,
            @RequestParam("moduleUrl") String url
            ){
        Menu menu = new Menu(menuId, icon, name, null, url, pid, order);
        boolean result = menuService.saveMenu(menu);

        Resp resp = new Resp();
        resp.setSuccess(result);
        return resp;
    }

    @DeleteMapping("/Module/delete")
    @ApiOperation("删除菜单")
    public Resp moduleDelete(@RequestParam("ids") List<Long> ids){
        boolean result = menuService.deleteMenuBatch(ids);
        Resp resp = new Resp();
        resp.setSuccess(result);
        return resp;
    }

    @GetMapping("/Module/get/{id}")
    @ApiOperation("根据菜单获取数据")
    public Resp moduleGet(@PathVariable("id") Long id){
        Menu menu = menuService.getMenuById(id);
        Resp resp = new Resp();
        HashMap<String, Object> data = new HashMap<>();
        data.put("data",MenuVoConvert.INSTANCE.menuToMenuVo(menu));
        resp.setData(data);
        return resp;
    }
}
