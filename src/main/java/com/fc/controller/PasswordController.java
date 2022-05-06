package com.fc.controller;

import com.fc.entity.Userlogin;
import com.fc.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PasswordController {
    @Autowired
    private UserloginService userloginService;

    @PostMapping("passwordRest")
    public ModelAndView passwordRest(String oldPassword, String password1, HttpSession session, ModelAndView mv) {

         String username = (String) session.getAttribute("username");

        Userlogin user = userloginService.findUser(username);

        if (user.getPassword().equals(oldPassword)){
            user.setPassword(password1);

            userloginService.update(user);

            mv.setViewName("redirect:/logout");
        }else {
            mv.addObject("message","密码错误");
            mv.setViewName("error");
        }

        return mv;
    }
}
