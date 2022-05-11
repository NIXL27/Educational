package com.fc.service.impl;

import com.fc.dao.StudentMapper;
import com.fc.entity.Student;
import com.fc.entity.StudentExample;
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

    @Override
    public Student findByid(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Student student) {
        studentMapper.insertSelective(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public void delete(Integer id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<StudentVO> findStudentByKeyword(String findStudentByName, Integer page, Integer pageSize) {

        String name = "%" + findStudentByName + "%";
        PageHelper.startPage(page, pageSize);
        List<StudentVO> students = studentMapper.findByKeyword(name);

        return students;
    }
}
