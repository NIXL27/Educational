package com.fc.service.impl;

import com.fc.dao.CollegeMapper;
import com.fc.entity.College;
import com.fc.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> findAll() {
        return collegeMapper.selectByExample(null);
    }
}
