package com.homemylove.convert;

import com.homemylove.entities.Dept;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.DeptVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeptVoConvert {

    DeptVoConvert INSTANCE = Mappers.getMapper(DeptVoConvert.class);

    @Mappings({
            @Mapping(source = "dept.editTime",target = "editTime"),
            @Mapping(source = "dept.deptId",target = "deptId"),

            @Mapping(source = "user.userName",target = "editUser")
    })
    DeptVo deptToDeptVo(Dept dept, User user);

}
