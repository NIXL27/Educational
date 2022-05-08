package com.fc.service;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.vo.SelectedcourseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    PageInfo<Course> page(Integer page, Integer pageSize);


    Course findByid(Integer id);


    List<SelectedcourseVo> findCourse(Integer id);

    void add(Course course);

    void update(Course course);

    void delete(Integer id);

    List<Course> findCourseByKeyword(String findCourseByName, Integer page, Integer pageSize);
}
