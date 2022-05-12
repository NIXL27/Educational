package com.fc.service;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.vo.SelectedcourseVo;

import java.util.List;

public interface SelectedcourseService {
    void update(Integer studentid, Integer courseid, String name, Integer mark);

    List<Selectedcourse> findCourseById(Integer id);

    void delete(Integer id);

    List<SelectedcourseVo> findCourseByMark(Integer id);

    List<Selectedcourse> findOne(Integer id, Integer studentId);

    void add(Integer id, Integer studentId);

    void deleteByStudent(Integer id, Integer studentId);
}
