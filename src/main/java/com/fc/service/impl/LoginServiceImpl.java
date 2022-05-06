package com.fc.service.impl;

import com.fc.dao.RoleMapper;
import com.fc.dao.UserloginMapper;
import com.fc.entity.Role;
import com.fc.entity.Userlogin;
import com.fc.entity.UserloginExample;
import com.fc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserloginMapper userloginMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role login(String username, String password) {

        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();

        criteria.andUsernameEqualTo(username);

        List<Userlogin> users = userloginMapper.selectByExample(userloginExample);

        if (users != null && users.size() != 0) {
            Userlogin user = users.get(0);

            if (user.getPassword().equals(password)) {
                return roleMapper.selectByPrimaryKey(user.getRole());
            }
        }else {
            System.out.println("123232");
        }

        return null;

    }
}
