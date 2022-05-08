package com.fc.service;

import com.fc.entity.Role;
import com.fc.entity.Userlogin;

import java.util.List;

public interface LoginService {

    Role login(String username, String password);

    List<Userlogin> findByusername(String username);

    void update(Userlogin user);
}
