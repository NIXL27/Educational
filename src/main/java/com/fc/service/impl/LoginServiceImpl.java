package com.fc.service.impl;

import com.fc.dao.UserloginMapper;
import com.fc.entity.Role;
import com.fc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Override
    public Role login(String username, String password) {
        return userloginMapper.findPermissions(username, password);
    }
}
