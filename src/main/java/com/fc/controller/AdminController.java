package com.fc.controller;

import com.fc.entity.*;
import com.fc.service.CollegeService;
import com.fc.service.Selectedstudent;
import com.fc.service.StudentService;
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
    private CollegeService collegeService;


    ModelAndView mv=new ModelAndView();
   //学生操作
    @RequestMapping("showStudent")
    public ModelAndView page(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize", required = true, defaultValue = "4") Integer pageSize) {
        List<StudentVO> students = studentService.findAllByPage(page, pageSize);

        PageInfo<StudentVO> pageInfo = new PageInfo<>(students);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/showStudent");
        return mv;
    }
    //学号验证
    @RequestMapping("checkStudentId")
    public Map<String, Object> checkStudentId (@RequestParam Integer id) {
        Map<String, Object> map = new HashMap<>();
        Student student = studentService.findByid(id);
        if (student != null) {
            map.put("flag",false);
            map.put("errorMsg","该学号已经存在，请重新输入!");
        }else {
            map.put("flag",true);
            map.put("errorMsg","");
        }

        return map;
    }
    // 添加页面显示
    @RequestMapping ("addStudent")
    public ModelAndView addStudent(ModelAndView mv) {
        List<College> collegeList = collegeService.findAll();
        List<Student> list = studentService.findAll();
        mv.addObject("collegeList",collegeList);
        mv.addObject("studentList",list);
        mv.setViewName("admin/addStudent");
        return mv;
    }
    // 添加课程操作
    @PostMapping("addStudent")
    public ModelAndView addStudent(Student student, ModelAndView mv) {
        // 验证学号
        Student checkStudentId = studentService.findByid(student.getUserid());
        if (checkStudentId == null) {
            studentService.add(student);
            mv.setViewName("redirect:/admin/showStudent");
        }else {
            mv.addObject("message","学号号重复");
            mv.setViewName("error");
        }
        return mv;
    }

    // 修改页面显示
    @GetMapping("editStudent")
    public ModelAndView editStudent(@RequestParam Integer id, ModelAndView mv) {

        if (id == null || id.equals("")) {
            mv.setViewName("redirect:/admin/showStudent");
        }else {
            Student student = studentService.findByid(id);
            List<College> collegeList = collegeService.findAll();
            List<Student> list = studentService.findAll();

            mv.addObject("student",student);
            mv.addObject("collegeList",collegeList);
            mv.addObject("studentList",list);

            mv.setViewName("admin/editStudent");
        }

        return mv;
    }

    // 修改学生操作
    @PostMapping("editStudent")
    public ModelAndView editStudent(Student student, ModelAndView mv) {
        studentService.update(student);
        mv.setViewName("redirect:/admin/showCourse");

        return mv;
    }
    // 删除学生操作
    @GetMapping("removeStudent")
    public ModelAndView removeStudent(@RequestParam Integer id, ModelAndView mv) {
        if (id == null || id.equals("")) {
            mv.setViewName("redirect:/admin/removeStudent");
        } else {

//       List<Selectedstudent> studentById = selectedstudentService.findCourseById(id);
//            if (!studentById.isEmpty()) {
//                mv.addObject("message","已删除对应学生");
//                mv.setViewName("error");
//            }else {
//                studentService.delete(id);
//                mv.setViewName("redirect:/admin/showCourse");
//            }
        }
            return mv;
        }
    // 学生名保存
    @RequestMapping("searchStudentName")
    public void searchStudentName(@RequestBody Student student, HttpSession session) {
        String studentname = student.getUsername();
        session.setAttribute("findStudentByName",studentname);
    }
    // 学生搜索
    @RequestMapping("selectStudent")
    public ModelAndView selectStudent(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                                     @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize, HttpSession session, ModelAndView mv) {

        String findStudentByName = (String) session.getAttribute("findStudentByName");

        List<Student> students = studentService.findStudentByKeyword(findStudentByName,page,pageSize);

        PageInfo<Student> selectStudentInfo = new PageInfo<>(students);

        mv.addObject("selectStudentInfo",selectStudentInfo);
        mv.setViewName("admin/selectStudent");
        return mv;
    }

}



