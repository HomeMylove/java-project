package com.homemylove.service;

import com.homemylove.entities.Dept;
import com.homemylove.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

public interface DeptService {
    Dept getDept(Long deptId);

}
