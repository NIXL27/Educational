package com.fc.service;

import com.fc.vo.SelectedcourseVo;

public interface SelectedcourseService {
    void update(Integer studentid, Integer courseid, String name, Integer mark);
}
