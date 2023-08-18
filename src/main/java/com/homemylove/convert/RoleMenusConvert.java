package com.homemylove.convert;

import com.homemylove.entities.Menu;
import com.homemylove.entities.vo.RoleMenusVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMenusConvert {

    RoleMenusConvert INSTANCE = Mappers.getMapper(RoleMenusConvert.class);

    @Mappings({
            @Mapping(source = "menu.menuId",target = "id"),
            @Mapping(source = "menu.menuName",target = "name")
    })
    RoleMenusVo toRoleMenusVo(Menu menu);

}
