package com.fc.controller;

import com.fc.entity.Course;
import com.fc.service.CourseService;
import com.fc.service.StudentService;
import com.fc.vo.StudentVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    // 教师操作
    @RequestMapping("showStudent")
    public ModelAndView showStudent(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page, @RequestParam(value = "pageSize", required = true, defaultValue = "4") Integer pageSize) {
        List<StudentVO> students = studentService.findAllByPage(page, pageSize);

        PageInfo<StudentVO> pageInfo = new PageInfo<>(students);

        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/showStudent");

        return mv;

    }


    // 课程操作
    @RequestMapping("showCourse")
    public ModelAndView showCourse(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page, @RequestParam(value = "pageSize", required = true, defaultValue = "4") Integer pageSize) {
        PageInfo<Course> coursePageInfo = courseService.page(page, pageSize);

        ModelAndView mv=new ModelAndView();
        mv.addObject("CoursePageInfo",coursePageInfo);
        mv.setViewName("admin/showCourse");

        return mv;

    }
}
