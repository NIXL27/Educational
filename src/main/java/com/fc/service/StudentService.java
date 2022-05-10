package com.fc.service;

import com.fc.vo.StudentVO;

import java.util.List;

public interface StudentService {
    // 分页查询学生对象
    List<StudentVO> findAllByPage(Integer page, Integer pageSize);
}
