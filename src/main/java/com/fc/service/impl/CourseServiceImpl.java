package com.fc.service.impl;

import com.fc.dao.CourseMapper;
import com.fc.dao.SelectedcourseMapper;
import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.entity.SelectedcourseExample;
import com.fc.entity.Student;
import com.fc.service.CourseService;
import com.fc.vo.SelectedcourseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    @Override
    public PageInfo<Course> page(Integer page, Integer pageSize) {

        //开启分页
        PageHelper.startPage(page,pageSize);

//

        List<Course> courses = courseMapper.selectByExample(null);
        return new PageInfo<>(courses);
    }


    @Override
    public Course findByid(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SelectedcourseVo> findCourse(Integer id) {

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
    public void add(Course course) {
        courseMapper.insertSelective(course);
    }

    @Override
    public void update(Course course) {
        courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public void delete(Integer id) {
        courseMapper.deleteByPrimaryKey(id);
    }

}
