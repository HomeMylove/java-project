package com.homemylove.convert;

import com.homemylove.entities.Role;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.RoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleVoConvert {

    RoleVoConvert INSTANCE = Mappers.getMapper(RoleVoConvert.class);

    @Mappings({
            @Mapping(source = "role.editTime",target = "editTime"),
            @Mapping(source = "role.roleId",target = "roleId"),

            @Mapping(source = "user.userName",target = "editUser")
    })
    RoleVo roleToRoleVo(Role role, User user);
}
