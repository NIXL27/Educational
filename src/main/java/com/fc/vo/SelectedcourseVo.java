package com.fc.vo;

import com.fc.entity.Selectedcourse;
import com.fc.entity.Student;

public class SelectedcourseVo {

    private Student studentCustom;

    private Selectedcourse selected;
    //判断该学生是否已经完成该课程
    private Boolean over = false;

    private Integer mark;

    private Integer studentid;

    private Integer courseid;

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    @Override
    public String toString() {
        return "SelectedcourseVo{" +
                "studentCustom=" + studentCustom +
                ", selected=" + selected +
                ", over=" + over +
                ", mark=" + mark +
                '}';
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Student getStudentCustom() {
        return studentCustom;
    }

    public void setStudentCustom(Student studentCustom) {
        this.studentCustom = studentCustom;
    }

    public Selectedcourse getSelected() {
        return selected;
    }

    public void setSelected(Selectedcourse selected) {
        this.selected = selected;
    }



    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

}
