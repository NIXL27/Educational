package com.fc.service;

import com.fc.entity.Role;
import com.fc.entity.Userlogin;

import java.util.List;

public interface UserloginService {

    // 登录（根据用户名和密码查询对应角色）
    Role login(String username, String password);

    // 根据用户名查询用户
    List<Userlogin> findByusername(String username);

    // 修改用户操作
    void update(Userlogin user);
}
