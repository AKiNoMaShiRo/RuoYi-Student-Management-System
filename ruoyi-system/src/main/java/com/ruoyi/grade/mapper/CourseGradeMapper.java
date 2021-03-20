package com.ruoyi.grade.mapper;

import com.ruoyi.grade.domain.CourseGradeClass;

import java.util.List;

public interface CourseGradeMapper {

    /**
     * 根据学号查询学生普通课程成绩
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<CourseGradeClass> selectByStuId(String studentId);

    /**
     * 根据学号查询普通课程成绩，可查多个学生
     *
     * @param courseGradeClass 学号
     * @return 结果
     */
    public List<CourseGradeClass> selectByGradeId(CourseGradeClass courseGradeClass);

}
