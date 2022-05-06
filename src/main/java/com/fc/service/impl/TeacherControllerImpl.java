package com.fc.service.impl;

import com.fc.dao.CourseMapper;
import com.fc.dao.SelectedcourseMapper;
import com.fc.dao.StudentMapper;
import com.fc.dao.TeacherMapper;
import com.fc.entity.*;
import com.fc.service.TeacherService;

import com.fc.vo.SelectedcourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherControllerImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

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
    public List<SelectedcourseVo> findAll(Integer id) {

        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andCourseidEqualTo(id);

        List<Selectedcourse> selectedcourses = selectedcourseMapper.selectByExample(selectedcourseExample);

        List<Student> students = selectedcourseMapper.selectStudentByCourse(id);

        System.out.println(students);


        List<SelectedcourseVo> selectedcourseVos = new ArrayList<>();


        for (int i = 0; i < selectedcourses.size(); i++) {
            SelectedcourseVo selectedcourseVo = new SelectedcourseVo();

            selectedcourseVo.setSelected(selectedcourses.get(i));
            selectedcourseVo.setStudentCustom(students.get(i));
            selectedcourseVo.setStudentid(students.get(i).getUserid());
            selectedcourseVo.setOver(selectedcourses.get(i).getMark() != null);
            selectedcourseVo.setMark(selectedcourses.get(i).getMark());
            selectedcourseVo.setCourseid(selectedcourses.get(i).getCourseid());

            selectedcourseVos.add(selectedcourseVo);
        }

        return selectedcourseVos;
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
}
