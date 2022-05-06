package com.fc.service.impl;

import com.fc.dao.UserloginMapper;
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

    @Override
    public Userlogin findUser(String username) {

        UserloginExample userloginExample = new UserloginExample();
        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<Userlogin> userlogins = userloginMapper.selectByExample(userloginExample);

        return userlogins.get(0);
    }

    @Override
    public void update(Userlogin user) {
        userloginMapper.updateByPrimaryKeySelective(user);
    }
}
