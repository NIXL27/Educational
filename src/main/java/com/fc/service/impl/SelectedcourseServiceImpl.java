package com.fc.service.impl;

import com.fc.dao.SelectedcourseMapper;
import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.entity.SelectedcourseExample;
import com.fc.service.SelectedcourseService;
import com.fc.vo.SelectedcourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedcourseServiceImpl implements SelectedcourseService {
    @Autowired
    private SelectedcourseMapper selectedcourseMapper;


    @Override
    public void update(Integer studentid, Integer courseid, String name, Integer mark) {

        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();
        Selectedcourse selectedcourse = new Selectedcourse();
        selectedcourse.setCourseid(courseid);
        selectedcourse.setStudentid(studentid);
        selectedcourse.setMark(mark);
        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();
        criteria.andCourseidEqualTo(courseid);
        criteria.andStudentidEqualTo(studentid);
        selectedcourseMapper.updateByExampleSelective(selectedcourse,selectedcourseExample);

    }

    @Override
    public List<Selectedcourse> findCourseById(Integer id) {
        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andCourseidEqualTo(id);

        return selectedcourseMapper.selectByExample(selectedcourseExample);
    }

    @Override
    public void delete(Integer id) {
        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andStudentidEqualTo(id);

        List<Selectedcourse> selectedcourses = selectedcourseMapper.selectByExample(selectedcourseExample);

        if (!selectedcourses.isEmpty()) {
            selectedcourseMapper.deleteByExample(selectedcourseExample);
        }
    }

    @Override
    public List<SelectedcourseVo> findCourseByMark(Integer id) {
        List<SelectedcourseVo> selectedcourseVos = selectedcourseMapper.findCourseByMark(id);

        for (SelectedcourseVo selectedcourseVo : selectedcourseVos) {
            selectedcourseVo.setCourseid(selectedcourseVo.getCouseCustom().getCourseid());
            if (selectedcourseVo.getMark() == null) {
                selectedcourseVo.setOver(false);
            }else {
                selectedcourseVo.setOver(true);
            }
        }

        return selectedcourseVos;
    }

    @Override
    public List<Selectedcourse> findOne(Integer id, Integer studentId) {
        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andStudentidEqualTo(studentId);
        criteria.andCourseidEqualTo(id);

        return selectedcourseMapper.selectByExample(selectedcourseExample);
    }

    @Override
    public void add(Integer id, Integer studentId) {
        Selectedcourse selectedcourse = new Selectedcourse();

        selectedcourse.setStudentid(studentId);
        selectedcourse.setCourseid(id);

        selectedcourseMapper.insertSelective(selectedcourse);
    }

    @Override
    public void deleteByStudent(Integer id, Integer studentId) {
        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andStudentidEqualTo(studentId);
        criteria.andCourseidEqualTo(id);

        selectedcourseMapper.deleteByExample(selectedcourseExample);
    }

}
