package com.fc.service.impl;

import com.fc.dao.SelectedcourseMapper;
import com.fc.dao.StudentMapper;
import com.fc.entity.*;
import com.fc.service.StudentService;
import com.fc.vo.SelectedcourseVo;
import com.fc.vo.StudentVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

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
    public List<Student> findAll() {
        return studentMapper.selectByExample(null);
    }
    @Override
    public void add(Student student) {
        studentMapper.insertSelective(student);
    }

    @Override
    public List<SelectedcourseVo> findStudent(Integer id) {
        return null;
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
    public List<Student> findStudentByKeyword(String findStudentByName, Integer page, Integer pageSize) {
        StudentExample studentExample = new StudentExample();

        StudentExample.Criteria criteria = studentExample.createCriteria();

        PageHelper.startPage(page, pageSize);
//        criteria.andStudentnameLike("%" + findStudentByName + "%");
        return studentMapper.selectByExample(studentExample);
    }


}






