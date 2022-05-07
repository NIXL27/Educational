package com.fc.service;

import com.fc.entity.Selectedcourse;

import java.util.List;

public interface SelectedcourseService {

    List<Selectedcourse> findStudentByMark(Integer id);
}
