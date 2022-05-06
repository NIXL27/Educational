package com.fc.service;

import com.fc.entity.Userlogin;

public interface UserloginService {
    Userlogin findUser(String username);

    void update(Userlogin user);
}
