package com.fc.service;

import com.fc.entity.Course;
import com.fc.entity.Teacher;
import com.fc.vo.SelectedcourseVo;

import java.util.List;

public interface TeacherService {
    List<Course> showCourse(String username);

    SelectedcourseVo finOne(Integer studentid, Integer courseid);

    List<Teacher> findAll();
}
