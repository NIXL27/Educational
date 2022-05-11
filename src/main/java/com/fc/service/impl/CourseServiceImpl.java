package com.fc.service.impl;

import com.fc.dao.CourseMapper;
import com.fc.entity.Course;
import com.fc.entity.CourseExample;
import com.fc.entity.Selectedcourse;
import com.fc.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public PageInfo<Course> page(Integer page, Integer pageSize) {

        //开启分页
        PageHelper.startPage(page,pageSize);

//

        List<Course> courses = courseMapper.selectByExample(null);
        return new PageInfo<>(courses);
    }

    @Override
    public PageInfo<Course> findByName(Integer page, Integer pageSize, String findCourseByName) {

        CourseExample example = new CourseExample();

        CourseExample.Criteria criteria = example.createCriteria();

        criteria.andCoursenameLike("%" + findCourseByName + "%");

        //开启分页
        PageHelper.startPage(page,pageSize);

        List<Course> list = courseMapper.selectByExample(example);


        return new PageInfo<>(list);
    }


}
