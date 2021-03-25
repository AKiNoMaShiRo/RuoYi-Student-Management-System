package com.ruoyi.grade.mapper;

import com.ruoyi.grade.domain.CourseGradeClass;
import com.ruoyi.grade.domain.GainedPoint;

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

    /**
     * 根据学号、课程类别查询学生已通过的课程
     *
     * @param courseGradeClass 学号、课程类别
     * @return 课程信息
     */
    public String selectPassPoint(CourseGradeClass courseGradeClass);

    /**
     * 查询有哪些课程名称
     *
     * @param courseName
     * @return 课程名称
     */
    public List<CourseGradeClass> selectCourseName(String courseName);

    /**
     * 查询某一个班级或某一专业某一课程的平均分
     *
     * @param courseGradeClass 课程名称和班级/专业信息
     * @return 平均分
     */
    public String selectCourseAvgByClass(CourseGradeClass courseGradeClass);

}
