package com.fc.service.impl;

import com.fc.dao.RoleMapper;
import com.fc.dao.UserloginMapper;
import com.fc.entity.Role;
import com.fc.entity.Userlogin;
import com.fc.entity.UserloginExample;
import com.fc.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserloginServiceImpl implements UserloginService {
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
        }

        return null;

    }

    @Override
    public List<Userlogin> findByusername(String username) {

        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();

        criteria.andUsernameEqualTo(username);

        return userloginMapper.selectByExample(userloginExample);
    }

    @Override
    public void update(Userlogin user) {
        userloginMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void add(Userlogin user) {
        userloginMapper.insertSelective(user);
    }

    @Override
    public void delete(Integer id) {
        String userName = String.valueOf(id);

        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();

        criteria.andUsernameEqualTo(userName);

        userloginMapper.deleteByExample(userloginExample);
    }
}
