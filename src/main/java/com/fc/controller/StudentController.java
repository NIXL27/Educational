package com.fc.controller;

import com.fc.entity.Course;
import com.fc.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private CourseService courseService;


    @GetMapping("showCourse")
    public ModelAndView page(ModelAndView mv,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "4") Integer pageSize
                                      ) {

//        //获取域对象中的course
//        String username = (String) session.getAttribute("username");
//
//        int id = Integer.parseInt(username);

        // 所有的日记
            PageInfo<Course> CoursePageInfo = courseService.page(page, pageSize);

            mv.addObject("CoursePageInfo",CoursePageInfo);

//

            mv.setViewName("student/showCourse");




        return mv;
    }
}
