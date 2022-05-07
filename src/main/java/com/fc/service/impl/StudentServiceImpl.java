package com.fc.service.impl;

import com.fc.dao.StudentMapper;
import com.fc.service.StudentService;
import com.fc.vo.StudentVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentVO> findAllByPage(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<StudentVO> students = studentMapper.findAll();

        return students;
    }
}
