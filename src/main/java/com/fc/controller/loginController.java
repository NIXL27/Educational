package com.fc.controller;

import com.fc.entity.Role;
import com.fc.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    private UserloginService loginService;

    @GetMapping("login")
    public String loginPage() {
        return "../../login";
    }

    @PostMapping("login")
    public String login(String username, String password, HttpSession session) {

        Role role = loginService.login(username, password);

        if (role != null) {
            session.setAttribute("username", username);

            if (role.getRolename().equals("admin")) {
                return "redirect:/admin/showStudent";
            } else if (role.getRolename().equals("teacher")) {
                return "redirect:/teacher/showCourse";
            } else if (role.getRolename().equals("student")) {
                return "redirect:/student/showCourse";
            }
        }

        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return "../../login";
    }
}
