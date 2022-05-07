package com.fc.service;

import com.fc.vo.StudentVO;

import java.util.List;

public interface StudentService {
    List<StudentVO> findAllByPage(Integer page, Integer pageSize);
}
