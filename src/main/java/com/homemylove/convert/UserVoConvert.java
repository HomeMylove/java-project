package com.homemylove.convert;

import com.homemylove.entities.Dept;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserVoConvert {

    UserVoConvert INSTANCE = Mappers.getMapper(UserVoConvert.class);

    @Mappings({
            @Mapping(source = "user.realName",target = "userRealName"),
            @Mapping(source = "user.sex",target = "userSex"),
            @Mapping(source = "user.mobile",target = "userMobile"),
            @Mapping(source = "user.email",target = "userEmail"),
    })
    UserVo userToUserVo(User user);

    @Mappings({
            @Mapping(source = "user.realName",target = "userRealName"),
            @Mapping(source = "user.sex",target = "userSex"),
            @Mapping(source = "user.mobile",target = "userMobile"),
            @Mapping(source = "user.email",target = "userEmail"),
            @Mapping(source = "user.editUser",target = "editUser"),
            @Mapping(source = "user.addUser",target = "addUser"),
            @Mapping(source = "user.editTime",target = "editTime"),
            @Mapping(source = "user.addTime",target = "addTime"),

            @Mapping(source = "dept.deptId",target = "deptId"),
            @Mapping(source = "dept.deptName",target = "deptName")
    })
    UserVo userToUserVo(User user, Dept dept);

}
