package com.fc.service;

import com.fc.entity.Selectedcourse;

import java.util.List;

public interface Selectedstudent {
    void update(Integer studentid, Integer courseid, String name, Integer mark);
    List<Selectedstudent> findStudentByMark(Integer id);

    List<Selectedstudent> findCourseById(Integer id);
}
