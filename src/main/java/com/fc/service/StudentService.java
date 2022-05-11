package com.fc.service;

import com.fc.entity.Student;
import com.fc.vo.StudentVO;

import java.util.List;

public interface StudentService {
    // 分页查询学生对象
    List<StudentVO> findAllByPage(Integer page, Integer pageSize);

    Student findByid(Integer id);

    void add(Student student);

    void update(Student student);

    void delete(Integer id);

    List<StudentVO> findStudentByKeyword(String findStudentByName, Integer page, Integer pageSize);
}
