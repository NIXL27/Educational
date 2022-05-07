package com.fc.service;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    PageInfo<Course> page(Integer page, Integer pageSize);


}
