package com.fc.controller;

import com.fc.entity.College;
import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.entity.Teacher;
import com.fc.service.*;
import com.fc.vo.StudentVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private SelectedcourseService selectedcourseService;

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





    /*课程操作*/

    // 课程展示
    @RequestMapping("showCourse")
    public ModelAndView showCourse(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page, @RequestParam(value = "pageSize", required = true, defaultValue = "4") Integer pageSize) {
        PageInfo<Course> coursePageInfo = courseService.page(page, pageSize);

        ModelAndView mv=new ModelAndView();
        mv.addObject("CoursePageInfo",coursePageInfo);
        mv.setViewName("admin/showCourse");

        return mv;
    }

    // 课号验证
    @RequestMapping("checkCourseId")
    @ResponseBody
    public Map<String, Object> checkCourseId (@RequestParam Integer id) {
        Map<String, Object> map = new HashMap<>();
        Course course = courseService.findByid(id);

        if (course != null) {
            map.put("flag",false);
            map.put("errorMsg","该课程号已经存在，请重新输入！");
        }else {
            map.put("flag",true);
            map.put("errorMsg","");
        }

        return map;
    }

    // 添加页面显示
    @GetMapping("addCourse")
    public ModelAndView addCourse(ModelAndView mv) {

        List<College> collegeList = collegeService.findAll();
        List<Teacher> list = teacherService.findAll();

        mv.addObject("collegeList",collegeList);
        mv.addObject("teacherList",list);
        mv.setViewName("admin/addCourse");
        return mv;
    }

    // 添加课程操作
    @PostMapping("addCourse")
    public ModelAndView addCourse(Course course, ModelAndView mv) {

        // 验证课号
        Course checkCourseId = courseService.findByid(course.getCourseid());

        if (checkCourseId == null) {
            courseService.add(course);
            mv.setViewName("redirect:/admin/showCourse");
        }else {
            mv.addObject("message","课程号重复");
            mv.setViewName("error");
        }
        return mv;
    }


    // 修改页面显示
    @GetMapping("editCourse")
    public ModelAndView editCourse(@RequestParam Integer id, ModelAndView mv) {

        if (id == null || id.equals("")) {
            mv.setViewName("redirect:/admin/showCourse");
        }else {
            Course course = courseService.findByid(id);
            List<College> collegeList = collegeService.findAll();
            List<Teacher> list = teacherService.findAll();

            mv.addObject("course",course);
            mv.addObject("collegeList",collegeList);
            mv.addObject("teacherList",list);

            mv.setViewName("admin/editCourse");
        }

        return mv;
    }

    // 修改课程操作
    @PostMapping("editCourse")
    public ModelAndView editCourse(Course course, ModelAndView mv) {
        courseService.update(course);
        mv.setViewName("redirect:/admin/showCourse");

        return mv;
    }

    // 删除课程操作
    @GetMapping("removeCourse")
    public ModelAndView removeCourse(@RequestParam Integer id, ModelAndView mv) {
        if (id == null || id.equals("")) {
            mv.setViewName("redirect:/admin/showCourse");
        }else {
            List<Selectedcourse> courseById = selectedcourseService.findCourseById(id);

            if (!courseById.isEmpty()) {
                mv.addObject("message","该课程有学生选课，请先通知学生退课");
                mv.setViewName("error");
            }else {
                courseService.delete(id);
                mv.setViewName("redirect:/admin/showCourse");
            }
        }
        return mv;
    }

}
