package com.fc.service;

import com.fc.entity.College;

import java.util.List;

public interface CollegeService {
    // 查询全部系别
    List<College> findAll();
}
