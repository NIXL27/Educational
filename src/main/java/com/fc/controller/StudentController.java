package com.fc.controller;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.service.CourseService;
import com.fc.service.SelectedcourseService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SelectedcourseService selectedcourseService;


    @GetMapping("showCourse")
    public ModelAndView page(ModelAndView mv,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "4") Integer pageSize
                                      ) {
        // 所有的课程
            PageInfo<Course> CoursePageInfo = courseService.page(page, pageSize);

            mv.addObject("CoursePageInfo",CoursePageInfo);

//

            mv.setViewName("student/showCourse");

            return mv;
    }
    @GetMapping("selectedCourse")
    public ModelAndView selectedCourse(ModelAndView mv,
                                       HttpSession session
                                       ) {

        Selectedcourse username = (Selectedcourse) session.getAttribute("username");

        Integer id = username.getStudentid();


        List<Selectedcourse> list = selectedcourseService.findStudentByMark(id);

        mv.addObject("selectedCourseList",list);

        mv.setViewName("student/selectCourse");
        return mv;
    }
}
