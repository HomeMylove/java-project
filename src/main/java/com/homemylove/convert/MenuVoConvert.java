package com.homemylove.convert;

import com.homemylove.entities.Menu;
import com.homemylove.entities.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuVoConvert {

    MenuVoConvert INSTANCE = Mappers.getMapper(MenuVoConvert.class);

    @Mappings({
            @Mapping(source = "menu.menuId",target = "menuid"),
            @Mapping(source = "menu.menuName",target = "menuname")
    })
    MenuVo menuToMenuVo(Menu menu);



}
