package com.fc.controller;

import com.fc.entity.Role;
import com.fc.entity.Userlogin;
import com.fc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public String loginPage() {
        return "../../login";
    }

    @PostMapping("login")
    public String login(@RequestBody String username, String password) {
        Role permissions = loginService.login(username, password);

        if (permissions.getRolename().equals("admin")) {
            return "redirect:/admin/showStudent";
        } else if (permissions.getRolename().equals("teacher")) {
            return "redirect:/teacher/showCourse";
        } else if (permissions.getRolename().equals("student")) {
            return "redirect:/student/showCourse";
        }
        return "login";
    }
}
