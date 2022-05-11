package com.fc.vo;

import com.fc.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVO extends Teacher {
    private String collegeName;
}
