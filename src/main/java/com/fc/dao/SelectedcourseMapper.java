package com.fc.dao;

import com.fc.entity.Course;
import com.fc.entity.Selectedcourse;
import com.fc.entity.SelectedcourseExample;
import java.util.List;

import com.fc.entity.Student;
import com.fc.vo.SelectedcourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedcourseMapper {
    long countByExample(SelectedcourseExample example);

    int deleteByExample(SelectedcourseExample example);

    int insert(Selectedcourse record);

    int insertSelective(Selectedcourse record);

    List<Selectedcourse> selectByExample(SelectedcourseExample example);

    int updateByExampleSelective(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);

    int updateByExample(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);

    List<Student> selectStudentByCourse(@Param("id") Integer id);

    //连表查询
    List<Selectedcourse> findStudentByMark(@Param("id") Integer id);


    List<SelectedcourseVo> findCourseByMark(Integer id);

    List<SelectedcourseVo> findOverCourseByMark(Integer id);
}