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

    List<SelectedcourseVo> findOverCourseByMark(Integer id);
}
