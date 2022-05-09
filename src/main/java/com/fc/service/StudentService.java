package com.fc.service;

import com.fc.entity.Course;
import com.fc.entity.Student;
import com.fc.vo.SelectedcourseVo;
import com.fc.vo.StudentVO;

import java.util.List;

public interface StudentService {
    List<StudentVO> findAllByPage(Integer page, Integer pageSize);
    Student findByid(Integer id);
    List<Student> findAll();
    void add(Student student);
    List<SelectedcourseVo> findStudent(Integer id);

    void update(Student student);

    void delete(Integer id);

    List<Student> findStudentByKeyword(String findStudentByName, Integer page, Integer pageSize);
}
