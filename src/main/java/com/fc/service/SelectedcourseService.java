package com.fc.service;

import com.fc.entity.Selectedcourse;

import java.util.List;

public interface SelectedcourseService {
    void update(Integer studentid, Integer courseid, String name, Integer mark);

    List<Selectedcourse> findStudentByMark(Integer id);
}
