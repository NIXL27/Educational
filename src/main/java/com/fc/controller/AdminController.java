package com.fc.controller;

import com.fc.entity.*;
import com.fc.service.*;
import com.fc.vo.StudentVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private LoginService loginService;

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


    // 课程名保存
    @RequestMapping("searchCourseName")
    public void searchCourseName(@RequestBody Student student, HttpSession session) {
        String username = student.getUsername();
        session.setAttribute("findCourseByName",username);
    }

    // 课程搜索
    @RequestMapping("selectCourse")
    public ModelAndView selectCourse(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                                     @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize, HttpSession session, ModelAndView mv) {

        String findCourseByName = (String) session.getAttribute("findCourseByName");

        List<Course> courses = courseService.findCourseByKeyword(findCourseByName,page,pageSize);

        PageInfo<Course> selectCourseInfo = new PageInfo<>(courses);

        mv.addObject("selectCourseInfo",selectCourseInfo);
        mv.setViewName("admin/selectCourse");
        return mv;
    }



    /*其他操作*/


    // 修改密码
    @GetMapping("passwordRest")
    public ModelAndView passwordRest(ModelAndView mv) {
        mv.setViewName("admin/passwordRest");
        return mv;
    }


    // 普通用户账号密码重置显示
    @GetMapping("userPasswordRest")
    public ModelAndView userPasswordRest(ModelAndView mv) {
        mv.setViewName("admin/userPasswordRest");
        return mv;
    }

    // 普通用户账号密码重置操作
    @PostMapping("userPasswordRest")
    public ModelAndView userPasswordRest(Userlogin userlogin, ModelAndView mv) {

        String username = userlogin.getUsername();
        List<Userlogin> users = loginService.findByusername(username);

        if (!users.isEmpty()) {

            Userlogin user = users.get(0);

            if (user.getRole() == 0) {
                mv.addObject("message","该账户为管理员账户，没法修改");
                mv.setViewName("error");
            }else {
                user.setPassword(userlogin.getPassword());
                loginService.update(user);
                mv.setViewName("admin/userPasswordRest");
            }
        }else {
            mv.addObject("message","没找到该用户");
            mv.setViewName("error");
        }

        return mv;
    }

}
