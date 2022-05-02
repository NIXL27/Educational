package com.fc.service;

import com.fc.entity.Role;

public interface LoginService {

    Role login(String username, String password);
}
