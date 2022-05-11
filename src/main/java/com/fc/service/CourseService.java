package com.fc.service;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.vo.SelectedcourseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    // 分页查询全部课程
    PageInfo<Course> page(Integer page, Integer pageSize);

    // 根据id查询课程
    Course findByid(Integer id);

    // 根据教师id查询对应课程
    List<SelectedcourseVo> findCourse(Integer id);

    // 添加课程
    void add(Course course);

    // 修改课程
    void update(Course course);

    // 删除课程
    void delete(Integer id);

    // 模糊查询课程
    List<Course> findCourseByKeyword(String findCourseByName, Integer page, Integer pageSize);
    PageInfo<Course> findByName(Integer page, Integer pageSize, String findCourseByName);
}
