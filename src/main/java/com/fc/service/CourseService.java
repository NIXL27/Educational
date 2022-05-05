package com.fc.service;

import com.fc.entity.Course;
import com.github.pagehelper.PageInfo;

public interface CourseService {
    PageInfo<Course> page(Integer page, Integer pageSize);
}
