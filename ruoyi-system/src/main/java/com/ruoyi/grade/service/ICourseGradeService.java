package com.ruoyi.grade.service;

import com.ruoyi.grade.domain.CourseGradeClass;

import java.util.List;

public interface ICourseGradeService {

//    /**
//     * 根据学号查询学生成绩
//     *
//     * @param studentId 学号
//     * @return 结果
//     */
//    public List<CourseGradeClass> selectByStuId(String studentId);

    /**
     * 根据学号查询普通课程成绩，可查多个学生
     *
     * @param courseGradeClass 学号
     * @return 结果
     */
    public List<CourseGradeClass> selectByGrade(CourseGradeClass courseGradeClass);

}
