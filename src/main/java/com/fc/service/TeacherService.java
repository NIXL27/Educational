package com.fc.service;

import com.fc.entity.Course;
import com.fc.vo.SelectedcourseVo;

import java.util.List;

public interface TeacherService {
    List<Course> showCourse(String username);

    List<SelectedcourseVo> findAll(Integer id);

    SelectedcourseVo finOne(Integer studentid, Integer courseid);
}
