package com.fc.service.impl;

import com.fc.dao.CourseMapper;
import com.fc.dao.SelectedcourseMapper;
import com.fc.dao.StudentMapper;
import com.fc.dao.TeacherMapper;
import com.fc.entity.*;
import com.fc.service.TeacherService;

import com.fc.vo.SelectedcourseVo;
import com.fc.vo.TeacherVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Course> showCourse(String username) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(Integer.parseInt(username));

        if (teacher != null) {

            CourseExample courseExample = new CourseExample();

            CourseExample.Criteria criteria = courseExample.createCriteria();

            criteria.andCollegeidEqualTo(teacher.getCollegeid());

            List<Course> courses = courseMapper.selectByExample(courseExample);

            System.out.println(courses);

            return courses;
        }
        return null;
    }


    @Override
    public SelectedcourseVo finOne(Integer studentid, Integer courseid) {
        SelectedcourseVo selectedcourseVo = new SelectedcourseVo();

        Student student = studentMapper.selectByPrimaryKey(studentid);

        selectedcourseVo.setStudentCustom(student);
        selectedcourseVo.setStudentid(studentid);
        selectedcourseVo.setCourseid(courseid);

        return selectedcourseVo;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.selectByExample(null);
    }

    @Override
    public List<TeacherVO> findAllByPage(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<TeacherVO> teachers = teacherMapper.findAllByPage();

        return teachers;
    }
}
