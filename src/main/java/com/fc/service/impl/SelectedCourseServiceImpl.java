package com.fc.service.impl;

import com.fc.dao.SelectedcourseMapper;
import com.fc.entity.Selectedcourse;
import com.fc.service.SelectedcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedCourseServiceImpl implements SelectedcourseService {
    @Autowired
    private SelectedcourseMapper selectedcourseMapper;




    @Override
    public List<Selectedcourse> findStudentByMark(Integer id) {
        return selectedcourseMapper.findStudentByMark(id);
    }
}
