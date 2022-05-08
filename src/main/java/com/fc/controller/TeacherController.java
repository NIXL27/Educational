package com.fc.controller;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.service.CourseService;
import com.fc.service.SelectedcourseService;
import com.fc.service.TeacherService;

import com.fc.vo.SelectedcourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SelectedcourseService selectedcourseService;

    @Autowired
    private CourseService courseService;

    // 显示我的课程
    @GetMapping(value = "showCourse")
    public ModelAndView stuCourseShow(HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView();

        String username = (String) session.getAttribute("username");

        List<Course> list = teacherService.showCourse(username);

        mv.addObject("courseList",list);
        mv.setViewName("teacher/showCourse");

        return mv;
    }

    @GetMapping("gradeCourse")
    public ModelAndView gradeCourse(Integer id, ModelAndView mv) {
        List<SelectedcourseVo> selectedcourseVos = courseService.findCourse(id);

        System.out.println(selectedcourseVos);

        mv.addObject("selectedCourseList",selectedcourseVos);

        mv.setViewName("teacher/showGrade");

        return mv;
    }

    @GetMapping("mark")
    public ModelAndView mark(Integer studentid, Integer courseid, ModelAndView mv) {

        SelectedcourseVo selectedcourseVo = teacherService.finOne(studentid, courseid);

        mv.addObject("selectedCourse",selectedcourseVo);

        mv.setViewName("teacher/mark");
        return mv;
    }

    @PostMapping("mark")
    public ModelAndView mark(Integer studentid, Integer courseid, String name, Integer mark,ModelAndView mv){
        selectedcourseService.update(studentid,courseid,name,mark);
        mv.setViewName("redirect:/teacher/gradeCourse?id=" + courseid);
        return mv;
    }


    @GetMapping("passwordRest")
    public ModelAndView passwordRest(ModelAndView mv) {
        mv.setViewName("teacher/passwordRest");
        return mv;
    }
}
