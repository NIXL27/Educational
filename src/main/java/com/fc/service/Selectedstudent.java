package com.fc.service;

import com.fc.entity.Selectedcourse;

import java.util.List;

public interface SelectedcourseService {
    // 打分操作
    void update(Integer studentid, Integer courseid, String name, Integer mark);

    List<Selectedcourse> findStudentByMark(Integer id);

    // 根据课程id查询
    List<Selectedcourse> findCourseById(Integer id);

    // 删除选、修课信息
    void delete(Integer id);
}
