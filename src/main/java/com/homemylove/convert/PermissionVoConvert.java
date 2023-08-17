package com.homemylove.convert;

import com.homemylove.entities.Permission;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.PermissionVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionVoConvert {
    PermissionVoConvert INSTANCE = Mappers.getMapper(PermissionVoConvert.class);


    @Mappings({
            @Mapping(source = "permission.editTime",target = "editTime"),
            @Mapping(source = "user.userName",target = "editUser")
    })
    PermissionVo toPermissionVo(Permission permission, User user);

}
